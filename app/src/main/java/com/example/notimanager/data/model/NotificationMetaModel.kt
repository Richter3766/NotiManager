package com.example.notimanager.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification_meta",
    foreignKeys = [
        ForeignKey(
            entity = NotificationModel::class,
            parentColumns = ["id"],
            childColumns = ["notificationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotificationMetaModel(
    @PrimaryKey
    val notificationId: Long,
    val intentActive: Boolean = true
)
