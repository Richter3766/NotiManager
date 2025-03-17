package com.notimanager.notimanager.data.repository

import com.notimanager.notimanager.data.model.AppIconModel
import com.notimanager.notimanager.data.model.NotificationIconModel
import com.notimanager.notimanager.data.model.NotificationMetaModel
import com.notimanager.notimanager.data.model.NotificationModel
import com.notimanager.notimanager.data.source.local.dao.AppIconDao
import com.notimanager.notimanager.data.source.local.dao.NotificationDao
import com.notimanager.notimanager.data.source.local.dao.NotificationIconDao
import com.notimanager.notimanager.data.source.local.dao.NotificationMetaDao
import com.notimanager.notimanager.domain.repository.NotificationRepositoryInterface

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

    override suspend fun getPriorityNotificationCount(appName: String): Int{
        return notificationDao.getPriorityNotificationCount(appName)

    }
}