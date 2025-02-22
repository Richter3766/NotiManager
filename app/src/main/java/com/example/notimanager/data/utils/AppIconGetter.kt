package com.example.notimanager.data.utils

import android.content.Context
import android.content.pm.PackageManager

object AppIconGetter {
    fun getAppIconResId(context: Context, packageName: String): Int {
        return try {
            val packageManager = context.packageManager
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            applicationInfo.icon
        } catch (e: PackageManager.NameNotFoundException) {
            0
        }
    }
}