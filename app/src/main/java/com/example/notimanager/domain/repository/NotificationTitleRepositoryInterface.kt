package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.NotificationTitle

interface NotificationTitleRepositoryInterface {
    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitle>
    suspend fun getNotificationTitlePriorityList(appName: String, title: String): List<NotificationTitle>
    suspend fun setTitlePriority(notificationId: Long, newPriority: Int): Long
    suspend fun removeTitlePriority(notificationId: Long): Long
}