package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationTitleUseCase(
    private val repository: NotificationRepositoryInterface
){
    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitle>{
        return repository.getNotificationTitleList(appName, title)
    }
}