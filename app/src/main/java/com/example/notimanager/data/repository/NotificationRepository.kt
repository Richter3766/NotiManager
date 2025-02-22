package com.example.notimanager.data.repository

import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao

class NotificationRepository(
    private val notificationDao: NotificationDao,
    private val notificationMetaDao: NotificationMetaDao,
    private val notificationIconDao: NotificationIconDao,
    private val appIconDao: AppIconDao
) : NotificationRepositoryInterface {
    override suspend fun insertNotification(notificationModel: NotificationModel): Long{
        return notificationDao.insert(notificationModel)
    }

    override suspend fun insertNotificationMeta(metaModel: NotificationMetaModel): Long{
        return notificationMetaDao.insert(metaModel)
    }

    override suspend fun insertNotificationIcon(notificationIconModel: NotificationIconModel): Long {
        return notificationIconDao.insert(notificationIconModel)
    }

    override suspend fun insertAppIcon(appIconModel: AppIconModel): Long {
        return appIconDao.insert(appIconModel)
    }
}