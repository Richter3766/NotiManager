package com.notimanager.notimanager.domain.model

import android.graphics.Bitmap

data class NotificationTitle(
    val id: Long,
    val title: String,
    val subText: String,
    val content: String,
    val timestamp: Long,
    val notificationIcon: Bitmap?,
    val priorityActive: Boolean,
    val priority: Int,
    val filteredId: Long,
    val unreadCount: Int
)
