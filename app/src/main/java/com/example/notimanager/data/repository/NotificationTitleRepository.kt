package com.example.notimanager.data.repository

import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationTitleRepositoryInterface

class NotificationTitleRepository(
    private val notificationDao: NotificationDao,
    private val notificationIconDao: NotificationIconDao
) : NotificationTitleRepositoryInterface {
    override suspend fun getNotificationTitleList(
        appName: String,
        title: String
    ): List<NotificationTitle>{
        return notificationDao.getNotificationTitleList(appName, title)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getNotificationTitlePriorityList(
        appName: String,
        title: String
    ): List<NotificationTitle> {
        return notificationDao.getNotificationTitlePriorityList(appName, title)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }
    override suspend fun setTitlePriority(
        notificationId: Long,
        newPriority: Int
    ): Long{
        return notificationIconDao.setPriority(notificationId, newPriority)
    }

    override suspend fun removeTitlePriority(
        notificationId: Long
    ):Long{
        return notificationIconDao.removePriority(notificationId)
    }

}