package com.example.notimanager.presentation.stateholder.viewmodel

import android.app.Activity
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

    private val _isNotificationPermissionGranted = MutableLiveData<Boolean>()
    val isNotificationPermissionGranted: LiveData<Boolean> get() = _isNotificationPermissionGranted

    init {
        checkNotificationPermission()
    }

    fun checkNotificationPermission() {
        _isNotificationPermissionGranted.value = notificationPermissionUseCase.isNotificationPermissionGranted()
    }

    fun requestPermission(activity: Activity) {
        notificationPermissionUseCase.requestPermission(activity)
    }
}
