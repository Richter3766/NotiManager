package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryDomainInterface

class NotificationTitleUseCase(
    private val repository: NotificationRepositoryDomainInterface
){
    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitle>{
        return repository.getNotificationTitleList(appName, title)
    }
}