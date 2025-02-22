package com.example.notimanager.data.repository

import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryDomainInterface

class NotificationRepositoryDomain(
    private val notificationDao: NotificationDao
) : NotificationRepositoryDomainInterface {
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