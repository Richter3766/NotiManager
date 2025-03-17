package com.notimanager.notimanager.domain.model

import android.graphics.Bitmap

data class FilteredNotification(
    val id: Long,
    val appName: String,
    val title: String,
    val appIcon: Bitmap?,
)


