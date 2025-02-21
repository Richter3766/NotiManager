package com.example.notimanager.data.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.repository.NotificationRepository
import com.example.notimanager.data.utils.NameGetter
import com.example.notimanager.data.utils.PendingIntentHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotiListenerService: NotificationListenerService() {
    lateinit var notificationRepository: NotificationRepository

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            val notification = it.notification
            val title = notification.extras.getString("android.title") ?: ""
            val content = notification.extras.getString("android.text") ?: ""
            val postTime = it.postTime
            val pendingIntent = notification.contentIntent
            val intentArray = pendingIntent?.let { pi -> PendingIntentHelper.savePendingIntent(pi) }

            val notificationModel = NotificationModel(
                appName = NameGetter.getAppName(this, it),
                title = title,
                content = content,
                timestamp = postTime
            )

            CoroutineScope(Dispatchers.IO).launch {
                val id = notificationRepository.insertNotification(notificationModel)
                val notificationMetaModel = NotificationMetaModel(
                    notificationId = id,
                    intentActive = true,
                    intentArray = intentArray ?: "".toByteArray()
                )
                notificationRepository.insertNotificationMeta(notificationMetaModel)
            }
        }
    }
}