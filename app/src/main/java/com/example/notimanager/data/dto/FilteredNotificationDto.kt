package com.example.notimanager.data.dto

import com.example.notimanager.domain.model.FilteredNotification

data class FilteredNotificationDto(
    val id: Long,
    val appName: String,
    val title: String,
){
    fun toDomain(): FilteredNotification {
        return FilteredNotification(
            id = this.id,
            appName = this.appName,
            title = this.title,
        )
    }
}


