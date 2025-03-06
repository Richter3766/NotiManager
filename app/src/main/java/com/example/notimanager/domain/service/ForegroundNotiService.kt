package com.example.notimanager.domain.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notimanager.R
import com.example.notimanager.presentation.ui.activity.MainActivity

class ForegroundNotiService: Service() {
    private val channelId = "NotiManagerChannel"
    private val groupId = "NotiManagerGroup"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val clearGroup = intent?.extras?.getBoolean("clearGroup") ?: false
        if (clearGroup) clearGroupNotifications()

        val appName = intent?.extras?.getString("appName") ?: ""
        val content = intent?.extras?.getString("content") ?: ""
        val isGroupSummary = intent?.extras?.getBoolean("isGroupSummary") ?: false
        putNotification(appName, content, isGroupSummary)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun putNotification(
        appName: String,
        content: String,
        isGroupSummary: Boolean) {
        val notificationIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("appName", appName)
        }
        val pendingIntent = PendingIntent
            .getActivity(this,
                0,
                notificationIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(appName)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setGroup(groupId)
            .setAutoCancel(true)
            .setOngoing(true)
            .setGroupSummary(isGroupSummary)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        NotificationManagerCompat.from(this).notify(appName.hashCode(), notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "NotiManager Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun clearGroupNotifications() {
        NotificationManagerCompat.from(this).cancelAll()
    }
}