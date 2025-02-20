package com.example.notimanager.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notification",
    indices = [Index(value = ["timestamp", "appName", "title"], unique = false)]
)
data class NotificationModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val intentArray: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationModel

        if (id != other.id) return false
        if (appName != other.appName) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (timestamp != other.timestamp) return false
        if (!intentArray.contentEquals(other.intentArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + appName.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + intentArray.contentHashCode()
        return result
    }
}