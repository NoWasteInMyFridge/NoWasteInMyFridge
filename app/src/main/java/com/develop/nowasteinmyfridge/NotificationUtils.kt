package com.develop.nowasteinmyfridge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat

class NotificationUtils(private val context: Context) {

    fun showNotification(message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel (for Android Oreo and higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "expiring_ingredients_channel"
            val channelName = "Expiring Ingredients"
            val channelDescription = "Notifications for expiring ingredients"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            notificationManager.createNotificationChannel(channel)
        }

        // Create the notification intent
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)


        // Create a vibration pattern
        val vibrationPattern = longArrayOf(0, 400, 200, 400)

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(context, "expiring_ingredients_channel")
            .setContentTitle("Expiring Ingredients")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Set small icon here
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(vibrationPattern) // Vibrate
            .setLights(0xff00ff00.toInt(), 300, 1000) // LED notification light

        // Show the notification
        val notificationId = 123
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}