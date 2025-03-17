package com.notimanager.notimanager.presentation.stateholder.state

import com.notimanager.notimanager.domain.model.NotificationTitle

data class NotificationTitlePriorityState(
    val notificationTitleList: List<NotificationTitle> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
