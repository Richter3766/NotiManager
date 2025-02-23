package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notimanager.domain.usecase.NotificationTitleUseCase
import com.example.notimanager.presentation.stateholder.state.NotificationTitleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationTitleViewModel @Inject constructor(
    private val notificationTitleUseCase: NotificationTitleUseCase
): ViewModel() {
    private val _notificationTitleState = MutableLiveData<NotificationTitleState>()
    val notificationTitleState: LiveData<NotificationTitleState> get() = _notificationTitleState

    private var appName: String = ""
    private var title: String = ""

    fun setArgs(appName: String, title: String) {
        this.appName = appName
        this.title = title
        loadNotificationTitles()
    }

    fun getAppName(): String{
        return appName
    }

    fun loadNotificationTitles() {
        viewModelScope.launch {
            _notificationTitleState.value = NotificationTitleState(isLoading = true)

            try {
                val notificationTitles = notificationTitleUseCase.getNotificationTitleList(appName, title)
                _notificationTitleState.value = NotificationTitleState(notificationTitleList = notificationTitles)
            } catch (e: Exception) {
                _notificationTitleState.value = NotificationTitleState(error = e.message)
            }
        }
    }
}