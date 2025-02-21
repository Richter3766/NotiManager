package com.example.notimanager.domain.repository

import android.content.Context

interface NotificationPermissionRepositoryInterface {
    fun isNotificationServiceEnabled(): Boolean
    fun requestPermission(context: Context)
}