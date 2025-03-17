package com.notimanager.notimanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification_icon",
    indices = [Index(value = ["priorityActive", "priority"], unique = false)],
    foreignKeys = [
        ForeignKey(
            entity = NotificationModel::class,
            parentColumns = ["id"],
            childColumns = ["notificationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotificationIconModel(
    @PrimaryKey
    val notificationId: Long,
    val iconBytes: ByteArray,
    @ColumnInfo(defaultValue = "0") val priorityActive: Boolean = false,
    @ColumnInfo(defaultValue = "0") val priority: Int = 0
)
