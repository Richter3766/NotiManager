package com.notimanager.notimanager.domain.usecase

import com.notimanager.notimanager.domain.model.Notification
import com.notimanager.notimanager.domain.repository.NotificationDomainRepositoryInterface

class NotificationUseCase(
    private val repository: NotificationDomainRepositoryInterface
){
    suspend fun getNotificationList(appName: String, title: String): List<Notification>{
        return repository.getNotificationList(appName, title)
    }

    suspend fun getNotificationSubTextList(appName: String, subText: String): List<Notification>{
        return repository.getNotificationSubTextList(appName, subText)
    }

    suspend fun deleteNotification(id: Long): Int{
        return repository.deleteNotification(id)
    }
}