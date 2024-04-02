package com.develop.nowasteinmyfridge.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.develop.nowasteinmyfridge.MainActivity
import com.develop.nowasteinmyfridge.NotificationUtils
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.domain.CheckExpirationUseCase
import com.google.firebase.messaging.Constants.MessagePayloadKeys.SENDER_ID
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltWorker
class ExpiryCheckWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val checkExpirationUseCase: CheckExpirationUseCase,
    private val notificationUtils: NotificationUtils,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val expiringIngredients = checkExpirationUseCase()
            if (expiringIngredients.isNotEmpty()) {
                // Trigger notification
                val message = buildNotificationMessage(expiringIngredients)
                notificationUtils.showNotification(message)
            }
            Result.success()
        } catch (e: Exception) {
            // Handle exceptions here
            Result.failure()
        }
    }

    private fun showNotification(expiringIngredients: List<Ingredient>) {
        // Create notification
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationId = 0 // Notification ID
        val channelId = "expiration_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Expiration Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Ingredients Expiring Soon")
            .setContentText("You have ${expiringIngredients.size} ingredients expiring soon.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun buildNotificationMessage(expiringIngredients: List<Ingredient>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("These ingredients are expiring soon:\n")
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        for (ingredient in expiringIngredients) {
            val formattedDate = dateFormat.format(ingredient.efd)
            stringBuilder.append("- ${ingredient.name}: $formattedDate\n")
        }
        return stringBuilder.toString()
    }


}