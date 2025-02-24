package com.example.notimanager.domain.model

import android.graphics.Bitmap

data class NotificationTitle(
    val title: String,
    val content: String,
    val timestamp: Long,
    val notificationIcon: Bitmap?
)
