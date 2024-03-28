package com.develop.nowasteinmyfridge.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ExpirationCheckScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun scheduleDailyCheck() {
        // Calculate the time for the daily check
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 15)
            set(Calendar.SECOND, 0)
        }

        // Calculate the initial delay to schedule the check
        val currentTime = Calendar.getInstance()
        val initialDelay = if (calendar.before(currentTime)) {
            calendar.add(Calendar.DATE, 1) // Schedule for tomorrow if the time has passed today
            calendar.timeInMillis - currentTime.timeInMillis
        } else {
            calendar.timeInMillis - currentTime.timeInMillis // Schedule for today if the time is in the future
        }

        // Set network constraints for the work request
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Build the periodic work request for the daily check
        val dailyCheckRequest = PeriodicWorkRequestBuilder<ExpiryCheckWorker>(
            repeatInterval = 1, // Repeat daily
            repeatIntervalTimeUnit = TimeUnit.DAYS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS) // Set the initial delay
            .setConstraints(constraints)
            .build()

        // Enqueue the periodic work request with WorkManager
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "DailyExpirationCheck",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyCheckRequest
        )
    }
}
