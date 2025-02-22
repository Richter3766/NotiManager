package com.example.notimanager.presentation.stateholder.state

import com.example.notimanager.domain.model.NotificationApp

data class NotificationAppState(
    val notificationAppList: List<NotificationApp> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
