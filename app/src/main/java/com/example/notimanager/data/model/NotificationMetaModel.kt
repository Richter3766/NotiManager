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
    val intentActive: Boolean = true,
    val intentArray: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotificationMetaModel

        if (notificationId != other.notificationId) return false
        if (intentActive != other.intentActive) return false
        if (!intentArray.contentEquals(other.intentArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = notificationId.hashCode()
        result = 31 * result + intentActive.hashCode()
        result = 31 * result + intentArray.contentHashCode()
        return result
    }
}

