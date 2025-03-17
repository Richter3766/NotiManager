package com.notimanager.notimanager.presentation.stateholder.state

import com.notimanager.notimanager.domain.model.FilteredNotification

data class FilteredNotificationState(
    val filteredList: List<FilteredNotification> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
