package com.example.notimanager.domain.model

import android.content.Intent
import android.graphics.Bitmap

data class Notification(
    val title: String,
    val subText: String,
    val content: String,
    val timestamp: Long,
    val intent: Intent?,
    val intentActive: Boolean,
    val notificationIcon: Bitmap?
)
