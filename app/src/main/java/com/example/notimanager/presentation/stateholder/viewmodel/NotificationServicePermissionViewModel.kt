package com.example.notimanager.presentation.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notimanager.domain.usecase.NotificationPermissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationServicePermissionViewModel @Inject constructor(
    private val notificationPermissionUseCase: NotificationPermissionUseCase
) : ViewModel() {

    private val _isNotificationServiceEnabled = MutableLiveData<Boolean>()
    val isNotificationServiceEnabled: LiveData<Boolean> get() = _isNotificationServiceEnabled

    init {
        checkNotificationServicePermission()
    }

    fun checkNotificationServicePermission() {
        _isNotificationServiceEnabled.value = notificationPermissionUseCase.isNotificationServiceEnabled()
    }

    fun requestServicePermission() {
        notificationPermissionUseCase.requestPermission()
        checkNotificationServicePermission()
    }
}
