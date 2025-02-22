package com.example.notimanager.presentation.stateholder.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notimanager.domain.usecase.NotificationPermissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationPermissionViewModel @Inject constructor(
    private val notificationPermissionUseCase: NotificationPermissionUseCase
) : ViewModel() {

    private val _isPermissionGranted = MutableLiveData<Boolean>()
    val isPermissionGranted: LiveData<Boolean> get() = _isPermissionGranted

    init {
        checkNotificationPermission()
    }

    fun checkNotificationPermission() {
        _isPermissionGranted.value = notificationPermissionUseCase.isNotificationServiceEnabled()
    }

    fun requestPermission() {
        notificationPermissionUseCase.requestPermission()
        checkNotificationPermission()
    }
}
