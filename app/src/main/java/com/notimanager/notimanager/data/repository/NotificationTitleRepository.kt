package com.notimanager.notimanager.data.repository

import com.notimanager.notimanager.data.source.local.dao.NotificationDao
import com.notimanager.notimanager.data.source.local.dao.NotificationIconDao
import com.notimanager.notimanager.domain.model.NotificationTitle
import com.notimanager.notimanager.domain.repository.NotificationTitleRepositoryInterface

class NotificationTitleRepository(
    private val notificationDao: NotificationDao,
    private val notificationIconDao: NotificationIconDao
) : NotificationTitleRepositoryInterface {
    override suspend fun getNotificationTitleList(
        appName: String,
    ): List<NotificationTitle>{
        return notificationDao.getNotificationTitleList(appName, false)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getNotificationTitlePriorityList(
        appName: String,
    ): List<NotificationTitle> {
        return notificationDao.getNotificationTitleList(appName, true)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }
    override suspend fun setTitlePriority(
        notificationId: Long,
        newPriority: Int
    ): Int{
        return notificationIconDao.setPriority(notificationId, newPriority)
    }

    override suspend fun removeTitlePriority(
        notificationId: Long
    ):Int{
        return notificationIconDao.removePriority(notificationId)
    }

    override suspend fun deleteByTitle(appName: String, title: String): Int {
        return notificationDao.deleteNotificationByTitle(appName, title)
    }

    override suspend fun deleteBySubText(appName: String, subText: String): Int {
        return notificationDao.deleteNotificationBySubText(appName, subText)
    }
    override suspend fun setTitleAsRead(appName: String, title: String): Int{
        return notificationDao.updateTitleAsRead(appName, title)
    }

    override suspend fun setSubTextAsRead(appName: String, subText: String): Int{
        return notificationDao.updateSubTextAsRead(appName, subText)
    }
}