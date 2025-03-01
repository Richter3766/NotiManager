package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationTitleRepositoryInterface

class NotificationTitleUseCase(
    private val repository: NotificationTitleRepositoryInterface
){
    suspend fun getNotificationTitleList(
        appName: String,
    ): List<NotificationTitle>{
        return repository.getNotificationTitleList(appName)
    }

    suspend fun getNotificationTitlePriorityList(
        appName: String,
    ): List<NotificationTitle> {
        return repository.getNotificationTitlePriorityList(appName)
    }

    suspend fun setTitlePriority(
        notificationId: Long,
        newPriority: Int
    ): Int {
        return repository.setTitlePriority(notificationId, newPriority)
    }

    suspend fun removeTitlePriority(
        notificationId: Long,
    ): Int {
        return repository.removeTitlePriority(notificationId)
    }
}