package com.example.notimanager.data.dto

import android.graphics.BitmapFactory
import com.example.notimanager.domain.model.NotificationTitle

data class NotificationTitleDto(
    val title: String,
    val content: String,
    val timestamp: Long,
    val iconBytes: ByteArray
) {
    fun toDomain(): NotificationTitle {
        return NotificationTitle(
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            notificationIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size)
        )
    }
}