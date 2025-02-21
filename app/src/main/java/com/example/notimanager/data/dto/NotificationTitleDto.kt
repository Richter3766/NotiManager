package com.example.notimanager.data.dto

import com.example.notimanager.domain.model.NotificationTitle

data class NotificationTitleDto(
    val title: String,
    val content: String,
    val timestamp: Long,
) {
    fun toDomain(): NotificationTitle {
        return NotificationTitle(
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
        )
    }
}