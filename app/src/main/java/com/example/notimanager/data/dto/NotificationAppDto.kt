package com.example.notimanager.data.dto

import com.example.notimanager.domain.model.NotificationApp

data class NotificationAppDto(
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val appIconResId: String
){
    fun toDomain(): NotificationApp {
        return NotificationApp(
            appName = this.appName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            appIconResId = this.appIconResId,
        )
    }
}
