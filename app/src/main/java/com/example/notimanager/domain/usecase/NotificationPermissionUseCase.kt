package com.example.notimanager.domain.usecase

import android.content.Context
import android.content.Intent
import android.provider.Settings

class NotificationPermissionUseCase(private val context: Context) {
    fun requestPermission() {
        if (!isNotificationServiceEnabled()) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            context.startActivity(intent)
        }
    }

    private fun isNotificationServiceEnabled(): Boolean {
        val pkgName = context.packageName
        val flat = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        return flat != null && flat.contains(pkgName)
    }
}