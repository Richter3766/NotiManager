package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notimanager.domain.usecase.NotificationAppUseCase
import com.example.notimanager.presentation.stateholder.state.NotificationAppPriorityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationAppPriorityViewModel @Inject constructor(
    private val notificationAppUseCase: NotificationAppUseCase
): ViewModel() {
    private val _notificationAppPriorityState = MutableLiveData<NotificationAppPriorityState>()
    val notificationAppPriorityState: LiveData<NotificationAppPriorityState> get() = _notificationAppPriorityState

    init {
        loadNotificationAppPriority()
    }

    fun loadNotificationAppPriority() {
        viewModelScope.launch {
            _notificationAppPriorityState.value = NotificationAppPriorityState(isLoading = true)

            try {
                val notificationApps = notificationAppUseCase.getNotificationAppPriorityList()
                _notificationAppPriorityState.value = NotificationAppPriorityState(notificationAppList = notificationApps)
            } catch (e: Exception) {
                _notificationAppPriorityState.value = NotificationAppPriorityState(error = e.message)
            }
        }
    }

    fun getLength(): Int{
        if (notificationAppPriorityState.value != null){
            return notificationAppPriorityState.value!!.notificationAppList.size
        }
        return 0
    }

    fun removeAppPriority(appName: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            notificationAppUseCase.removeAppPriority(appName)
            loadNotificationAppPriority()
            onComplete()
        }
    }

    fun deleteApp(appName: String) {
        viewModelScope.launch {
            notificationAppUseCase.deleteNotificationApp(appName)
            loadNotificationAppPriority()
        }
    }
}