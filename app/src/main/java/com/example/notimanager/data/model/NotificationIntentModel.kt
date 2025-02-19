package com.example.notimanager.data.model

import android.content.Intent
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.notimanager.common.objects.JsonMapper.objectMapper

@Entity(
    tableName = "notification_intent",
    foreignKeys = [
        ForeignKey(
            entity = NotificationModel::class,
            parentColumns = ["id"],
            childColumns = ["notificationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotificationIntentModel(
    @PrimaryKey
    val notificationId: Long,
    val action: String,
    val packageName: String,
    val extras: String?,
    val isActive: Boolean = true
){
    companion object {
        fun fromIntent(intent: Intent, id: Long): NotificationIntentModel {
            val extrasMap = intent.extras?.keySet()?.associateWith { intent.extras?.getString(it) }
            val extrasJson = extrasMap?.let { objectMapper.writeValueAsString(it) }

            return NotificationIntentModel(
                notificationId = id,
                action = intent.action ?: "",
                packageName = intent.`package` ?: "",
                extras = extrasJson,
            )
        }
    }
}
