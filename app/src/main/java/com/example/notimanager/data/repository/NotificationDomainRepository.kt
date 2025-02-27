package com.example.notimanager.data.repository

import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationDomainRepositoryInterface

class NotificationDomainRepository(
    private val notificationDao: NotificationDao
) : NotificationDomainRepositoryInterface {
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