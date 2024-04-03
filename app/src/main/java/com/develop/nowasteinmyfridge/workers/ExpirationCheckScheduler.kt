package com.develop.nowasteinmyfridge.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
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
        val workRequest = buildDailyCheckWorkRequest()

        enqueueOneTimeWorkRequest(workRequest)
    }

    private fun buildDailyCheckWorkRequest(): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        return OneTimeWorkRequestBuilder<ExpiryCheckWorker>()
            .setConstraints(constraints)
            .build()
    }

    private fun enqueueOneTimeWorkRequest(workRequest: OneTimeWorkRequest) {
        try {
            WorkManager.getInstance(context).enqueue(workRequest)
            Log.d("ExpirationCheckScheduler", "Daily check triggered successfully")
        } catch (e: Exception) {
            Log.e("ExpirationCheckScheduler", "Error triggering daily check: ${e.message}")
            e.printStackTrace()
        }
    }
}