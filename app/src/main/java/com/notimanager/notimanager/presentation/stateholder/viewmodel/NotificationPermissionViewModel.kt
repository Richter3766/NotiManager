package com.notimanager.notimanager.presentation.stateholder.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.lifecycle.ViewModel
import com.notimanager.notimanager.domain.usecase.NotificationPermissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class NotificationPermissionViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val notificationPermissionUseCase: NotificationPermissionUseCase
) : ViewModel() {
    private val prefs = "do_permission"
    private val sharedPreferences = appContext.getSharedPreferences(prefs, Context.MODE_PRIVATE)

    fun requestPermission(activity: Activity) {
        if (!checkNotificationPermission()) {
            notificationPermissionUseCase.requestPermission(activity)
            sharedPreferences.edit().putBoolean(prefs, true).apply()
        }
        else openAppSettings(activity)
    }

    fun checkNotificationPermission(): Boolean {
        return sharedPreferences.getBoolean(prefs, false)
    }

    private fun openAppSettings(activity: Activity) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }
}
