package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.NotificationTitle

interface NotificationTitleRepositoryInterface {
    suspend fun getNotificationTitleList(appName: String): List<NotificationTitle>
    suspend fun getNotificationTitlePriorityList(appName: String): List<NotificationTitle>
    suspend fun setTitlePriority(notificationId: Long, newPriority: Int): Int
    suspend fun removeTitlePriority(notificationId: Long): Int
    suspend fun deleteByTitle(appName: String, title: String): Int
    suspend fun deleteBySubText(appName: String, subText: String): Int
    suspend fun setTitleAsRead(appName: String, title: String): Int
    suspend fun setSubTextAsRead(appName: String, subText: String): Int
}