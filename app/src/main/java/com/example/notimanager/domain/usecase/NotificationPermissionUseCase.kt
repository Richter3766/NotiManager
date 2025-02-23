package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface

class NotificationPermissionUseCase(
    private val repository: NotificationPermissionRepositoryInterface
) {
    fun isNotificationServiceEnabled(): Boolean {
        return repository.isNotificationServiceEnabled()
    }

    fun requestPermission() {
        if (!repository.isNotificationServiceEnabled()) {
            repository.requestPermission()
        }
    }
}