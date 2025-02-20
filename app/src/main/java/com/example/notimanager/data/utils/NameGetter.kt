package com.example.notimanager.data.utils

import android.content.Context
import android.content.pm.PackageManager
import android.service.notification.StatusBarNotification

object NameGetter {
    fun getAppName(context: Context, sbn: StatusBarNotification): String {
        val packageName = sbn.packageName
        val packageManager = context.packageManager

        val appInfo = try {
            packageManager.getApplicationInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }

        return appInfo?.let {
            packageManager.getApplicationLabel(it).toString()
        } ?: "Unknown App"
    }
}