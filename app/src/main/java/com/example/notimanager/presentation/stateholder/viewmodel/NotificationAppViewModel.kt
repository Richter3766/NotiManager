package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notimanager.domain.usecase.NotificationAppUseCase
import com.example.notimanager.presentation.stateholder.state.NotificationAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationAppViewModel @Inject constructor(
    private val notificationAppUseCase: NotificationAppUseCase
): ViewModel() {
    private val _notificationAppState = MutableLiveData<NotificationAppState>()
    val notificationAppState: LiveData<NotificationAppState> get() = _notificationAppState

    init {
        loadNotificationApps()
    }

    fun loadNotificationApps() {
        viewModelScope.launch {
            _notificationAppState.value = NotificationAppState(isLoading = true)

            try {
                val notificationApps = notificationAppUseCase.getNotificationAppList()
                _notificationAppState.value = NotificationAppState(notificationAppList = notificationApps)
            } catch (e: Exception) {
                _notificationAppState.value = NotificationAppState(error = e.message)
            }
        }
    }

    fun setAppPriority(appName: String, newPriority: Int = 0, onComplete: () -> Unit) {
        viewModelScope.launch {
            notificationAppUseCase.setAppPriority(appName, newPriority)
            loadNotificationApps()
            onComplete()
        }
    }

    fun deleteNotificationApp(appName: String) {
        viewModelScope.launch {
            notificationAppUseCase.deleteNotificationApp(appName)
            loadNotificationApps()
        }
    }
}