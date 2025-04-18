package com.notimanager.notimanager.domain.repository

import com.notimanager.notimanager.domain.model.Notification

interface NotificationDomainRepositoryInterface {
    suspend fun getNotificationList(appName: String, title: String): List<Notification>
    suspend fun getNotificationSubTextList(appName: String, subText: String): List<Notification>
    suspend fun deleteNotification(id: Long): Int
}