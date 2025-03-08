package com.example.notimanager.data.dto

import android.graphics.BitmapFactory
import com.example.notimanager.domain.model.FilteredNotification

data class FilteredNotificationDto(
    val id: Long,
    val appName: String,
    val title: String,
    val iconBytes: ByteArray,
){
    fun toDomain(): FilteredNotification {
        return FilteredNotification(
            id = this.id,
            appName = this.appName,
            title = this.title,
            appIcon = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.size),
        )
    }
}


