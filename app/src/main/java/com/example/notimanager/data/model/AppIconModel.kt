package com.example.notimanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_icon" ,
    indices = [Index(value = ["priorityActive", "priority"], unique = false)]
)
data class AppIconModel(
    @PrimaryKey
    val notiAppName: String,
    val iconBytes: ByteArray,
    @ColumnInfo(defaultValue = "0") val priorityActive: Boolean = false,
    @ColumnInfo(defaultValue = "0") val priority: Int = 0
)
