package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationDomainRepositoryInterface

class NotificationUseCase(
    private val repository: NotificationDomainRepositoryInterface
){
    suspend fun getNotificationList(appName: String, title: String): List<Notification>{
        return repository.getNotificationList(appName, title)
    }

    suspend fun getNotificationSubTextList(appName: String, subText: String): List<Notification>{
        return repository.getNotificationSubTextList(appName, subText)
    }
}