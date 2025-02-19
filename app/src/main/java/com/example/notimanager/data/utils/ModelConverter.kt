package com.example.notimanager.data.utils

import android.content.Intent
import com.example.notimanager.common.objects.JsonMapper.objectMapper
import com.example.notimanager.data.model.NotificationIntentModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.domain.model.Notification

object ModelConverter {
    fun Notification.toModel(): NotificationModel {
        return NotificationModel(
            packageName = this.packageName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
        )
    }

    fun NotificationModel.toDomain(intent: Intent): Notification {
        return Notification(
            packageName = this.packageName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            intent = intent
        )
    }
}