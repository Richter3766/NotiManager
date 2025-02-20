package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.Notification

interface NotificationRepositoryInterface {
    suspend fun getAllNotifications(): List<Notification>
}