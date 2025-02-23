package com.example.notimanager.data.dto

import com.example.notimanager.data.utils.PendingIntentHelper.retrievePendingIntent
import com.example.notimanager.domain.model.Notification

data class NotificationDto(
    val title: String,
    val content: String,
    val timestamp: Long,
    val intentActive: Boolean,
    val intentArray: ByteArray,
    val notificationIconResId: String
){
    fun toDomain(): Notification {
        return Notification(
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            intent = retrievePendingIntent(intentArray),
            intentActive = this.intentActive,
            notificationIconResId = this.notificationIconResId
        )
    }
}
