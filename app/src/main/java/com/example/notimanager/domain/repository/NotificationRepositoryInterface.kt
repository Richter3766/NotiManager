package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle

interface NotificationRepositoryInterface {
    suspend fun getNotificationAppList(): List<NotificationApp>
    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitle>
    suspend fun getNotificationList(appName: String, title: String): List<Notification>
}