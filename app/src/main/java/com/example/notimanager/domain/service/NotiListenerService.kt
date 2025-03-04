package com.example.notimanager.domain.service

import android.graphics.drawable.Icon
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.domain.repository.FilteredNotificationRepositoryInterface
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
import com.example.notimanager.domain.utils.AppIconGetter.convertByteArray
import com.example.notimanager.domain.utils.AppIconGetter.convertByteArrayWithColor
import com.example.notimanager.domain.utils.AppIconGetter.getAppIcon
import com.example.notimanager.domain.utils.NameGetter
import com.example.notimanager.domain.utils.IntentHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@AndroidEntryPoint
class NotiListenerService: NotificationListenerService() {
    @Inject lateinit var notificationRepository: NotificationRepositoryInterface
    @Inject lateinit var filterRepository: FilteredNotificationRepositoryInterface
    private val mutex = Mutex()

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) return

        val notification = sbn.notification

        val appName = NameGetter.getAppName(this, sbn)
        val title = notification.extras.getString("android.title") ?: ""
        val subText = notification.extras.getString("android.subText") ?: ""
        val content = notification.extras.getString("android.text") ?: ""
        val postTime = sbn.postTime

        CoroutineScope(Dispatchers.IO).launch {
            if (title == "" && content == "" && subText == "") return@launch

            val filteredList = filterRepository.getSpecificFilteredList(appName, title, subText)
            if (filteredList.isNotEmpty()) return@launch

            mutex.withLock {
                val id = insertNotification(appName, title, content, postTime, subText)
                insertNotificationMeta(id, sbn.packageName)
                insertNotificationIcon(id, notification.getLargeIcon(), notification.smallIcon, notification.color)
                insertAppIcon(appName, sbn.packageName)
            }
        }
    }

    private suspend fun insertNotification(
        appName: String,
        title: String,
        content: String,
        postTime: Long,
        subText: String
    ): Long{
        val notificationModel = NotificationModel(
            appName = appName,
            title = title,
            content = content,
            timestamp = postTime,
            subText = subText
        )
        return notificationRepository.insertNotification(notificationModel)
    }

    private suspend fun insertNotificationMeta(
        id: Long,
        notificationPackage: String?,
    ){
        val intent = packageManager.getLaunchIntentForPackage(notificationPackage ?: "")
        val intentArray = intent?.let { IntentHelper.saveIntent(it) }

        val notificationMetaModel = NotificationMetaModel(
            notificationId = id,
            intentActive = true,
            intentArray = intentArray ?: "".toByteArray()
        )
        notificationRepository.insertNotificationMeta(notificationMetaModel)
    }

    private suspend fun insertNotificationIcon(
        id: Long,
        iconLarge: Icon?,
        iconSmall: Icon?,
        color: Int
    ){
        val byteArray = if (iconLarge != null) {
            convertByteArray(iconLarge.loadDrawable(this@NotiListenerService))
        } else {
            convertByteArrayWithColor(iconSmall?.loadDrawable(this@NotiListenerService), color)
                ?: ByteArray(0)
        }

        val notificationIconModel = NotificationIconModel(
            notificationId = id,
            iconBytes = byteArray
        )
        notificationRepository.insertNotificationIcon(notificationIconModel)
    }

    private suspend fun insertAppIcon(
        appName: String,
        packageName: String
    ){
        val byteArray = convertByteArray(getAppIcon(this@NotiListenerService, packageName))

        val appIconModel = AppIconModel(
            notiAppName = appName,
            iconBytes = byteArray
        )
        notificationRepository.insertAppIcon(appIconModel)
    }
}

