package com.example.notimanager.presentation.stateholder.state

import com.example.notimanager.domain.model.Notification

data class NotificationState(
    val notificationList: List<Notification> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
