package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationUseCase(
    private val repository: NotificationRepositoryInterface
) {
    suspend fun add(notification: Notification) {
        repository.addNotification(notification)
    }

    suspend fun get(): List<Notification> {
        return repository.getAllNotifications()
    }
}