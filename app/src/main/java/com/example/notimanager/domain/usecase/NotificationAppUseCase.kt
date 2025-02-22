package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.repository.NotificationRepositoryDomainInterface

class NotificationAppUseCase(
    private val repository: NotificationRepositoryDomainInterface
){
    suspend fun getNotificationAppList(): List<NotificationApp>{
        return repository.getNotificationAppList()
    }
}