package com.notimanager.notimanager.presentation.stateholder.state

import com.notimanager.notimanager.domain.model.NotificationApp

data class NotificationAppPriorityState(
    val notificationAppList: List<NotificationApp> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
