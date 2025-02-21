package com.example.notimanager.domain.model

data class NotificationApp(
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
)
