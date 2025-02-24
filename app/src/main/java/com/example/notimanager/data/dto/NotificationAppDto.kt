package com.example.notimanager.data.dto

import android.graphics.BitmapFactory
import com.example.notimanager.domain.model.NotificationApp

data class NotificationAppDto(
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val iconBytes: ByteArray
){
    fun toDomain(): NotificationApp {
        return NotificationApp(
            appName = this.appName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            appIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size),
        )
    }
}
