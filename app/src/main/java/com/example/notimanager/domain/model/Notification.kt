package com.example.notimanager.domain.model

import android.app.PendingIntent
import android.graphics.Bitmap

data class Notification(
    val title: String,
    val content: String,
    val timestamp: Long,
    val intent: PendingIntent?,
    val intentActive: Boolean,
    val notificationIcon: Bitmap?
)
