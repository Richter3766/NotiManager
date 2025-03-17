package com.notimanager.notimanager.domain.model

import android.graphics.Bitmap

data class NotificationApp(
    val appName: String,
    val title: String,
    val content: String,
    val timestamp: Long,
    val appIcon: Bitmap?,
    val priorityActive: Boolean,
    val priority: Int,
    val filteredId: Long,
    val isRead: Boolean
)
