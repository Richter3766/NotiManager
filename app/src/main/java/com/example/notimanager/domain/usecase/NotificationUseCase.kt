package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationRepositoryDomainInterface

class NotificationUseCase(
    private val repository: NotificationRepositoryDomainInterface
){
    suspend fun getNotificationList(appName: String, title: String): List<Notification>{
        return repository.getNotificationList(appName, title)
    }
}