package com.example.notimanager.data.repository

import android.content.Intent
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.data.utils.ModelConverter.toDomain
import com.example.notimanager.data.utils.PendingIntentHelper.retrievePendingIntent
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationRepository(
    private val notificationDao: NotificationDao,
    private val notificationMetaDao: NotificationMetaDao
) : NotificationRepositoryInterface {
    override suspend fun getAllNotifications(): List<Notification> {
        return notificationDao.getAll()
            .asSequence()
            .map { it.toDomain(retrievePendingIntent(it.intentArray)!!) }
            .toList()
    }

    suspend fun insertNotification(notificationModel: NotificationModel): Long{
        return notificationDao.insert(notificationModel)
    }

    suspend fun insertNotificationMeta(metaModel: NotificationMetaModel): Long{
        return notificationMetaDao.insert(metaModel)
    }

    suspend fun getAppList(): List<Notification>{
        return notificationDao.getAppList()
            .asSequence()
            .map { it.toDomain(retrievePendingIntent(it.intentArray)!!) }
            .toList()

    }
}