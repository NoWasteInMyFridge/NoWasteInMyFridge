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
    @ApplicationContext private val context: Context
) {
    fun scheduleDailyCheck() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 5) // Set the hour to 9 AM
            set(Calendar.MINUTE, 55) // Set the minute to 0
        }

        val currentTime = Calendar.getInstance()
        val initialDelay = if (calendar.before(currentTime)) {
            // If the specified time has already passed today, schedule it for tomorrow
            calendar.add(Calendar.DATE, 1)
            calendar.timeInMillis - currentTime.timeInMillis
        } else {
            // If the specified time is in the future today, schedule it for today
            calendar.timeInMillis - currentTime.timeInMillis
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val dailyCheckRequest = PeriodicWorkRequestBuilder<ExpiryCheckWorker>(
            repeatInterval = 1, // Repeat daily
            repeatIntervalTimeUnit = TimeUnit.DAYS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS) // Set the initial delay to the calculated value
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "DailyExpirationCheck",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyCheckRequest
        )
    }
}
