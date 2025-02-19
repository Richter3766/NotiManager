package com.example.notimanager.data.repository

import android.content.Intent
import com.example.notimanager.data.model.NotificationIntentModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIntentDao
import com.example.notimanager.data.utils.ModelConverter.toModel
import com.example.notimanager.data.utils.ModelConverter.toDomain
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationRepositoryInterface

class NotificationRepository(
    private val notificationDao: NotificationDao,
    private val notificationIntentDao: NotificationIntentDao
) : NotificationRepositoryInterface {
    override suspend fun addNotification(notification: Notification) {
        val model = notification.toModel()
        val notificationId = notificationDao.insert(model)
        val intentModel = NotificationIntentModel.fromIntent(notification.intent, notificationId)
        notificationIntentDao.insert(intentModel)
    }

    override suspend fun getAllNotifications(): List<Notification> {
        return notificationDao.getAll()
            .asSequence()
            .map { it.toDomain(Intent()) }
            .toList()
    }
}