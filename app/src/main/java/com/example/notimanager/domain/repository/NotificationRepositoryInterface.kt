package com.example.notimanager.domain.repository

import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel

interface NotificationRepositoryInterface {
    suspend fun insertNotification(notificationModel: NotificationModel): Long
    suspend fun insertNotificationMeta(metaModel: NotificationMetaModel): Long
    suspend fun insertNotificationIcon(notificationIconModel: NotificationIconModel): Long
    suspend fun insertAppIcon(appIconModel: AppIconModel): Long
    suspend fun getPriorityNotificationCount(appName: String): Int
}