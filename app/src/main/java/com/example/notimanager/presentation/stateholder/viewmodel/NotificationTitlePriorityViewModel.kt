package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notimanager.domain.usecase.NotificationTitleUseCase
import com.example.notimanager.presentation.stateholder.state.NotificationTitlePriorityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationTitlePriorityViewModel @Inject constructor(
    private val notificationTitleUseCase: NotificationTitleUseCase
): ViewModel() {
    private val _notificationTitlePriorityState = MutableLiveData<NotificationTitlePriorityState>()
    val notificationTitlePriorityState: LiveData<NotificationTitlePriorityState> get() = _notificationTitlePriorityState

    private var appName: String = ""
    private var title: String = ""

    fun setArgs(appName: String, title: String) {
        this.appName = appName
        this.title = title
        loadNotificationTitles()
    }

    fun getLength(): Int{
        if (notificationTitlePriorityState.value != null){
            return notificationTitlePriorityState.value!!.notificationTitleList.size
        }
        return 0
    }

    fun loadNotificationTitles() {
        viewModelScope.launch {
            _notificationTitlePriorityState.value = NotificationTitlePriorityState(isLoading = true)

            try {
                val notificationTitles = notificationTitleUseCase.getNotificationTitlePriorityList(appName, title)
                _notificationTitlePriorityState.value = NotificationTitlePriorityState(notificationTitleList = notificationTitles)
            } catch (e: Exception) {
                _notificationTitlePriorityState.value = NotificationTitlePriorityState(error = e.message)
            }
        }
    }

    fun removeTitlePriority(notificationId: Long, onComplete: () -> Unit) {
        viewModelScope.launch {
            notificationTitleUseCase.removeTitlePriority(notificationId)
            loadNotificationTitles()
            onComplete()
        }
    }
}