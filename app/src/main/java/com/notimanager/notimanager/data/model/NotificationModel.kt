package com.notimanager.notimanager.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification",
    indices = [
        Index(value = ["appName", "title", "timestamp"], unique = false),
        Index(value = ["appName", "subText", "timestamp"], unique = false)
    ]
)
data class NotificationModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val subText: String,
    val isRead: Boolean = false,
)
