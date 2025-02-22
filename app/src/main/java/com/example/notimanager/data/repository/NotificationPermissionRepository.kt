package com.example.notimanager.data.repository

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import javax.inject.Inject

class NotificationPermissionRepository @Inject constructor(
    private val appContext: Context
): NotificationPermissionRepositoryInterface {
    override fun isNotificationServiceEnabled(): Boolean {
        val pkgName = appContext.packageName
        val flat = Settings.Secure.getString(appContext.contentResolver, "enabled_notification_listeners")
        return flat != null && flat.contains(pkgName)
    }

    override fun requestPermission() {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
    }
}