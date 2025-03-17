package com.notimanager.notimanager.domain.repository

interface NotificationPermissionRepositoryInterface {
    fun isNotificationServiceEnabled(): Boolean
    fun requestNotificationListenerPermission()
    fun isNotificationPermissionGranted(): Boolean
}