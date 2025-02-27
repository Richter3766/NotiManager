package com.example.notimanager.domain.repository

interface NotificationPermissionRepositoryInterface {
    fun isNotificationServiceEnabled(): Boolean
    fun requestPermission()
}