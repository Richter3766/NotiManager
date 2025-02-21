package com.example.notimanager.domain.model

import android.app.PendingIntent

data class Notification(
    val packageName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val intent: PendingIntent
)
