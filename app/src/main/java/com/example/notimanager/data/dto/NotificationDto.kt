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
            intent = retrievePendingIntent(intentArray)!!,
            intentActive = this.intentActive,
            notificationIconResId = this.notificationIconResId
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationDto

        if (title != other.title) return false
        if (content != other.content) return false
        if (timestamp != other.timestamp) return false
        if (intentActive != other.intentActive) return false
        if (!intentArray.contentEquals(other.intentArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + intentActive.hashCode()
        result = 31 * result + intentArray.contentHashCode()
        return result
    }
}
