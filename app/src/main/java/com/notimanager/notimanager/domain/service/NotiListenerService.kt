package com.notimanager.notimanager.domain.service

import android.content.Intent
import android.graphics.drawable.Icon
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.notimanager.notimanager.R
import com.notimanager.notimanager.data.model.AppIconModel
import com.notimanager.notimanager.data.model.NotificationIconModel
import com.notimanager.notimanager.data.model.NotificationMetaModel
import com.notimanager.notimanager.data.model.NotificationModel
import com.notimanager.notimanager.domain.repository.FilteredNotificationRepositoryInterface
import com.notimanager.notimanager.domain.repository.NotificationRepositoryInterface
import com.notimanager.notimanager.domain.utils.AppIconGetter.convertByteArray
import com.notimanager.notimanager.domain.utils.AppIconGetter.convertByteArrayWithColor
import com.notimanager.notimanager.domain.utils.AppIconGetter.getAppIcon
import com.notimanager.notimanager.domain.utils.IntentHelper
import com.notimanager.notimanager.domain.utils.NameGetter
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
        val title = notification.extras.getCharSequence("android.title")?.toString() ?: ""
        val subText = notification.extras.getString("android.subText") ?: ""
        val content = notification.extras.getCharSequence("android.text")?.toString() ?: ""
        val postTime = sbn.postTime

        CoroutineScope(Dispatchers.IO).launch {
            if (appName == "NotiManager") return@launch
            if (title == "" && content == "") return@launch

            val filteredList = filterRepository.getSpecificFilteredList(appName, title, subText)
            if (filteredList.isNotEmpty()) return@launch

            mutex.withLock {
                val id = insertNotification(appName, title, content, postTime, subText)
                insertNotificationMeta(id, sbn.packageName)
                insertNotificationIcon(id, notification.getLargeIcon(), notification.smallIcon, notification.color)
                insertAppIcon(appName, sbn.packageName)
                putNotification(appName)
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
        // TODO: 원래 pendingIntent의 extras 추출해서 넣어보기
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

    private suspend fun putNotification(appName: String){
        val count = notificationRepository.getPriorityNotificationCount(appName)
        if (count != 0){
            val serviceIntent = Intent(this, ForegroundNotiService::class.java).apply {
                putExtra("appName", appName)
                putExtra("content", getString(R.string.status_app_content, count.toString()))
            }
            startService(serviceIntent)
        }
        else{
            val basicTitle = getString(R.string.status_basic_title)
            val notiContent = getString(R.string.status_noti_content)
            val serviceIntent = Intent(this, ForegroundNotiService::class.java).apply {
                putExtra("appName", basicTitle)
                putExtra("content", notiContent)
                putExtra("isGroupSummary", true)
            }
            startService(serviceIntent)
        }
    }
}

