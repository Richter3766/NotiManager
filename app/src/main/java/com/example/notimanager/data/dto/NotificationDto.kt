package com.example.notimanager.data.dto

import android.graphics.BitmapFactory
import com.example.notimanager.domain.utils.IntentHelper.retrieveIntent
import com.example.notimanager.domain.model.Notification

data class NotificationDto(
    val title: String,
    val subText: String,
    val content: String,
    val timestamp: Long,
    val intentActive: Boolean,
    val intentArray: ByteArray,
    val iconBytes: ByteArray
){
    fun toDomain(): Notification {
        return Notification(
            title = this.title,
            subText = this.subText,
            content = this.content,
            timestamp = this.timestamp,
            intent = retrieveIntent(intentArray),
            intentActive = this.intentActive,
            notificationIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size)
        )
    }
}
