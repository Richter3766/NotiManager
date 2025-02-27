package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.Notification

interface NotificationDomainRepositoryInterface {
    suspend fun getNotificationList(appName: String, title: String): List<Notification>
}