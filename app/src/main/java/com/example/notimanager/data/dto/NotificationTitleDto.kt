package com.example.notimanager.data.dto

import android.graphics.BitmapFactory
import com.example.notimanager.domain.model.NotificationTitle

data class NotificationTitleDto(
    val id: Long,
    val title: String,
    val subText: String,
    val content: String,
    val timestamp: Long,
    val iconBytes: ByteArray,
    val priorityActive: Boolean,
    val priority: Int
) {
    fun toDomain(): NotificationTitle {
        return NotificationTitle(
            id = this.id,
            title = this.title,
            content = this.content,
            subText = this.subText,
            timestamp = this.timestamp,
            notificationIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size),
            priorityActive = this.priorityActive,
            priority = this.priority
        )
    }
}