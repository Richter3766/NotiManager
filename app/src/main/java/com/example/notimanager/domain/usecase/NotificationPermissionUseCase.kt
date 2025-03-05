package com.example.notimanager.domain.usecase

import android.Manifest
import android.app.Activity
import android.os.Build
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface

class NotificationPermissionUseCase(
    private val repository: NotificationPermissionRepositoryInterface
) {
    fun isNotificationServiceEnabled(): Boolean {
        return repository.isNotificationServiceEnabled()
    }

    fun requestPermission() {
        if (!repository.isNotificationServiceEnabled()) {
            repository.requestNotificationListenerPermission()
        }
    }

    fun isNotificationPermissionGranted(): Boolean{
        return repository.isNotificationPermissionGranted()
    }

    fun requestPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Android 12 이하에서는 별도 권한이 필요하지 않음
        }
    }

    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1
    }
}