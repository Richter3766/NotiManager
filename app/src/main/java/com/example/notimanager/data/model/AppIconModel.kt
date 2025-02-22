package com.example.notimanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_icon"
)
data class AppIconModel(
    @PrimaryKey
    val notiAppName: String,
    val appIconResId: Int
)
