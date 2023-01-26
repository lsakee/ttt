package com.example.myapplication;

import android.Manifest
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager
import android.util.Log;
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.myapplication.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

class FirebaseCloudMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message);
        //수신 된 메세지 처리
        Log.d("Message", "onMessageReceived");
        createNotificationChannel()
        sendNotification("테스트")
    }

    private fun createNotificationChannel() {
        val name = "name"
        val descriptionText = "description"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel =
            NotificationChannel("channel", name, importance).apply {
                description = descriptionText
            }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    private fun sendNotification(body: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this,  System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder =
            NotificationCompat.Builder(this, "channel")

        builder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("title")
            .setContentText(body)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(pendingIntent, true)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }
}