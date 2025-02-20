package com.example.notimanager.data.utils

import android.content.Intent
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.domain.model.Notification

object ModelConverter {
    fun NotificationModel.toDomain(intent: Intent): Notification {
        return Notification(
            packageName = this.appName,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp,
            intent = intent
        )
    }
}