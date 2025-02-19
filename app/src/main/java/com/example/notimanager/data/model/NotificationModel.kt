package com.example.notimanager.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification",
    indices = [Index(value = ["timestamp", "title"], unique = false)]
)
data class NotificationModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val packageName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
)