package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationTitleRepositoryInterface

class NotificationTitleUseCase(
    private val repository: NotificationTitleRepositoryInterface
){
    suspend fun getNotificationTitleList(
        appName: String,
        title: String
    ): List<NotificationTitle>{
        return repository.getNotificationTitleList(appName, title)
    }

    suspend fun getNotificationTitlePriorityList(
        appName: String,
        title: String
    ): List<NotificationTitle> {
        return repository.getNotificationTitlePriorityList(appName, title)
    }

    suspend fun setTitlePriority(
        notificationId: Long,
        newPriority: Int
    ): Long {
        return repository.setTitlePriority(notificationId, newPriority)
    }

    suspend fun removeTitlePriority(
        notificationId: Long,
    ): Long {
        return repository.removeTitlePriority(notificationId)
    }
}