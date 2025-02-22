package com.example.notimanager.data.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.repository.NotificationRepositoryInterface
import com.example.notimanager.data.utils.AppIconGetter.getAppIconResId
import com.example.notimanager.data.utils.NameGetter
import com.example.notimanager.data.utils.PendingIntentHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotiListenerService: NotificationListenerService() {
    @Inject
    lateinit var notificationRepository: NotificationRepositoryInterface

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            val notification = it.notification
            val title = notification.extras.getString("android.title") ?: ""
            val content = notification.extras.getString("android.text") ?: ""
            val postTime = it.postTime
            val pendingIntent = notification.contentIntent
            val intentArray = pendingIntent?.let { pi -> PendingIntentHelper.savePendingIntent(pi) }

            val appName = NameGetter.getAppName(this, it)
            val notificationModel = NotificationModel(
                appName = appName,
                title = title,
                content = content,
                timestamp = postTime
            )

            val notificationIconResId = notification.smallIcon.resId
            val appIconResId = getAppIconResId(this, it.packageName)

            CoroutineScope(Dispatchers.IO).launch {
                val id = notificationRepository.insertNotification(notificationModel)

                launch {
                    val notificationMetaModel = NotificationMetaModel(
                        notificationId = id,
                        intentActive = true,
                        intentArray = intentArray ?: "".toByteArray()
                    )
                    notificationRepository.insertNotificationMeta(notificationMetaModel)
                }
                launch {
                    val notificationIconModel = NotificationIconModel(
                        notificationId = id,
                        notificationIconResId = notificationIconResId
                    )
                    notificationRepository.insertNotificationIcon(notificationIconModel)
                }
                launch {
                    val appIconModel = AppIconModel(
                        appIconResId = appIconResId,
                        notiAppName = appName
                    )
                    notificationRepository.insertAppIcon(appIconModel)
                }
            }
        }
    }
}

