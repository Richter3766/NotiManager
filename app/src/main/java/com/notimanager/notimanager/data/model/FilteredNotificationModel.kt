package com.notimanager.notimanager.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "filtered_notification" ,
    indices = [Index(value = ["appName", "title"], unique = false)]
)
data class FilteredNotificationModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val appName: String,
    val title: String,
)


