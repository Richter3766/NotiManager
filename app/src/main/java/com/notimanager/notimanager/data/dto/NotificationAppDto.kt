package com.notimanager.notimanager.data.dto

import android.graphics.BitmapFactory
import com.notimanager.notimanager.domain.model.NotificationApp

data class NotificationAppDto(
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val iconBytes: ByteArray,
    val priorityActive: Boolean,
    val priority: Int,
    val filteredId: Long = 0L,
    val isRead: Boolean
){
    fun toDomain(): NotificationApp {
        return NotificationApp(
            appName = this.appName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            appIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size),
            priorityActive = this.priorityActive,
            priority = this.priority,
            filteredId = this.filteredId,
            isRead = this.isRead
        )
    }
}
