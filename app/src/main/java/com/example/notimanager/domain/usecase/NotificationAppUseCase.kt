package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.repository.NotificationAppRepositoryInterface

class NotificationAppUseCase(
    private val repository: NotificationAppRepositoryInterface
){
    suspend fun getNotificationAppList(): List<NotificationApp>{
        return repository.getNotificationAppList()
    }

    suspend fun getNotificationAppPriorityList(): List<NotificationApp>{
        return repository.getNotificationAppPriorityList()
    }

    suspend fun setAppPriority(
        appName: String,
        newPriority: Int
    ): Int {
        return repository.setAppPriority(appName, newPriority)
    }

    suspend fun removeAppPriority(
        appName: String,
    ): Int {
        return repository.removeAppPriority(appName)
    }
}