package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationUseCase(
    private val repository: NotificationRepositoryInterface
){
    suspend fun getNotificationAppList(): List<NotificationApp>{
        return repository.getNotificationAppList()
    }

    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitle>{
        return repository.getNotificationTitleList(appName, title)
    }

    suspend fun getNotificationList(appName: String, title: String): List<Notification>{
        return repository.getNotificationList(appName, title)
    }
}