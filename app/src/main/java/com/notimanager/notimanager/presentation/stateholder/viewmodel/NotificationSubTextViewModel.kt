package com.notimanager.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notimanager.notimanager.domain.usecase.NotificationUseCase
import com.notimanager.notimanager.presentation.stateholder.state.NotificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationSubTextViewModel @Inject constructor(
    private val notificationUseCase: NotificationUseCase
): ViewModel() {
    private val _notificationState = MutableLiveData<NotificationState>()
    val notificationState: LiveData<NotificationState> get() = _notificationState

    private var appName: String = ""
    private var subText: String = ""

    fun setArgs(appName: String, subText: String) {
        this.appName = appName
        this.subText = subText
        loadNotification()
    }

    fun getAppName(): String{
        return appName
    }

    fun loadNotification() {
        viewModelScope.launch {
            _notificationState.value = NotificationState(isLoading = true)

            try {
                val notification = notificationUseCase.getNotificationSubTextList(appName, subText)
                _notificationState.value = NotificationState(notificationList = notification)
            } catch (e: Exception) {
                _notificationState.value = NotificationState(error = e.message)
            }
        }
    }

    fun deleteNotification(id: Long){
        viewModelScope.launch {
            notificationUseCase.deleteNotification(id)
            loadNotification()
        }
    }
}