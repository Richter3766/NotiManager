package com.notimanager.notimanager.domain.repository

import com.notimanager.notimanager.domain.model.NotificationApp

interface NotificationAppRepositoryInterface {
    suspend fun getNotificationAppList(): List<NotificationApp>
    suspend fun getNotificationAppPriorityList(): List<NotificationApp>
    suspend fun setAppPriority(appName: String, newPriority: Int): Int
    suspend fun removeAppPriority(appName: String): Int
    suspend fun deleteNotificationApp(appName: String): Int
}