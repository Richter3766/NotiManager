package com.notimanager.notimanager.data.dto

import android.graphics.BitmapFactory
import com.notimanager.notimanager.domain.model.NotificationTitle

data class NotificationTitleDto(
    val id: Long,
    val title: String,
    val subText: String,
    val content: String,
    val timestamp: Long,
    val iconBytes: ByteArray,
    val priorityActive: Boolean,
    val priority: Int,
    val filteredId: Long = 0L,
    val unreadCount: Int
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
            priority = this.priority,
            filteredId = this.filteredId,
            unreadCount = unreadCount
        )
    }
}