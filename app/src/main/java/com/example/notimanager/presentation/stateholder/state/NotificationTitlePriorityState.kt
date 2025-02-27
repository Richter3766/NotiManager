package com.example.notimanager.presentation.stateholder.state

import com.example.notimanager.domain.model.NotificationTitle

data class NotificationTitlePriorityState(
    val notificationTitleList: List<NotificationTitle> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
