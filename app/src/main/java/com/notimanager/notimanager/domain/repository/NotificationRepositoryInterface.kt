package com.notimanager.notimanager.domain.repository

import com.notimanager.notimanager.data.model.AppIconModel
import com.notimanager.notimanager.data.model.NotificationIconModel
import com.notimanager.notimanager.data.model.NotificationMetaModel
import com.notimanager.notimanager.data.model.NotificationModel

interface NotificationRepositoryInterface {
    suspend fun insertNotification(notificationModel: NotificationModel): Long
    suspend fun insertNotificationMeta(metaModel: NotificationMetaModel): Long
    suspend fun insertNotificationIcon(notificationIconModel: NotificationIconModel): Long
    suspend fun insertAppIcon(appIconModel: AppIconModel): Long
    suspend fun getPriorityNotificationCount(appName: String): Int
}