package com.example.notimanager.data.repository

import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationRepository(
    private val notificationDao: NotificationDao,
    private val notificationMetaDao: NotificationMetaDao
) : NotificationRepositoryInterface {
    suspend fun insertNotification(notificationModel: NotificationModel): Long{
        return notificationDao.insert(notificationModel)
    }

    suspend fun insertNotificationMeta(metaModel: NotificationMetaModel): Long{
        return notificationMetaDao.insert(metaModel)
    }

    override suspend fun getNotificationAppList(): List<NotificationApp>{
        return notificationDao.getNotificationAppList()
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getNotificationTitleList(
        appName: String,
        title: String
    ): List<NotificationTitle>{
        return notificationDao.getNotificationTitleList(appName, title)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getNotificationList(
        appName: String,
        title: String
    ): List<Notification>{
        return notificationDao.getNotificationList(appName, title)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

}