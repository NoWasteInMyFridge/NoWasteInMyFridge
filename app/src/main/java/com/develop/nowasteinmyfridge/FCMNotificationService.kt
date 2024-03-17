package com.develop.nowasteinmyfridge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Context

class FCMNotificationService : FirebaseMessagingService(){

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if the message contains a notification payload.
        remoteMessage.notification?.let {
            // Handle notification message.
            val title = it.title ?: ""
            val body = it.body ?: ""

            // Display the notification.
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "DefaultChannelId"
        val channelName = "DefaultChannelId"
        val notificationId = 0

        // Create an Intent for the notification
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Create a notification manager
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for devices with Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Build the notification
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        // Display the notification
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}