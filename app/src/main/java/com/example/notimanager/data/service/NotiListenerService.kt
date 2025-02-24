package com.example.notimanager.data.service

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.notimanager.common.objects.DateFormatter.toBitmap
import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.repository.NotificationRepositoryInterface
import com.example.notimanager.data.utils.AppIconGetter.convertByteArray
import com.example.notimanager.data.utils.NameGetter
import com.example.notimanager.data.utils.PendingIntentHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class NotiListenerService: NotificationListenerService() {
    @Inject
    lateinit var notificationRepository: NotificationRepositoryInterface

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            val notification = it.notification
            val postTime = it.postTime
            val appName = NameGetter.getAppName(this, it)

            val title = notification.extras.getString("android.title") ?: ""
            val content = notification.extras.getString("android.text") ?: ""

            val pendingIntent = notification.contentIntent
            val intentArray = pendingIntent?.let { pi -> PendingIntentHelper.savePendingIntent(pi) }

            CoroutineScope(Dispatchers.IO).launch {
                val id = insertNotification(appName, title, content, postTime)

                launch {
                    insertNotificationMeta(id, intentArray)
                }

                launch {
                    insertNotificationIcon(id, notification.getLargeIcon())
                }

                launch {
                    insertAppIcon(appName, notification.smallIcon)
                }
            }
        }
    }
    private suspend fun insertNotification(
        appName: String,
        title: String,
        content: String,
        postTime: Long
    ): Long{
        val notificationModel = NotificationModel(
            appName = appName,
            title = title,
            content = content,
            timestamp = postTime
        )
        return notificationRepository.insertNotification(notificationModel)
    }

    private suspend fun insertNotificationMeta(
        id: Long,
        intentArray: ByteArray?
    ){
        val notificationMetaModel = NotificationMetaModel(
            notificationId = id,
            intentActive = true,
            intentArray = intentArray ?: "".toByteArray()
        )
        notificationRepository.insertNotificationMeta(notificationMetaModel)
    }

    private suspend fun insertNotificationIcon(id: Long, icon: Icon?){
        val byteArray = convertByteArray(this@NotiListenerService, icon)

        val notificationIconModel = NotificationIconModel(
            notificationId = id,
            iconBytes = byteArray
        )
        notificationRepository.insertNotificationIcon(notificationIconModel)
    }

    private suspend fun insertAppIcon(
        appName: String,
        icon: Icon?
    ){
        val byteArray = convertByteArray(this@NotiListenerService, icon)
        val appIconModel = AppIconModel(
            notiAppName = appName,
            iconBytes = byteArray
        )
        notificationRepository.insertAppIcon(appIconModel)
    }
}

