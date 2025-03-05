package com.example.notimanager.data.repository

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
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

    override fun requestNotificationListenerPermission() {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
    }

    override fun isNotificationPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            appContext.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
}