package com.example.notimanager.data.repository

import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.repository.NotificationAppRepositoryInterface

class NotificationAppRepository(
    private val notificationDao: NotificationDao,
    private val appIconDao: AppIconDao
) : NotificationAppRepositoryInterface {
    override suspend fun getNotificationAppList(): List<NotificationApp>{
        return notificationDao.getNotificationAppList(false)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getNotificationAppPriorityList(): List<NotificationApp> {
        return notificationDao.getNotificationAppList(true)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun setAppPriority(
        appName: String,
        newPriority: Int
    ): Int {
        return appIconDao.setPriority(appName, newPriority)
    }

    override suspend fun removeAppPriority(
        appName: String,
    ): Int {
        return appIconDao.removePriority(appName)
    }
    override suspend fun deleteNotificationApp(
        appName: String
    ): Int{
        return notificationDao.deleteNotificationByAppName(appName)
    }
}