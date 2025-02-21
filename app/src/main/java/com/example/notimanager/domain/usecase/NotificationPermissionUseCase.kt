package com.example.notimanager.domain.usecase

import android.content.Context
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface

class NotificationPermissionUseCase(
    private val repository: NotificationPermissionRepositoryInterface
) {
    fun isNotificationServiceEnabled(): Boolean {
        return repository.isNotificationServiceEnabled()
    }

    fun requestPermission(context: Context) {
        if (!isNotificationServiceEnabled()) {
            repository.requestPermission(context)
        }
    }
}