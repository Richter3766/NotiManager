package com.example.notimanager.domain.model

import android.content.Intent

data class Notification(
    val packageName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val intent: Intent
)
