package com.example.notimanager.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.data.source.local.database.NotiManagerDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationRepositoryTest {

    private lateinit var notificationDao: NotificationDao
    private lateinit var notificationMetaDao: NotificationMetaDao
    private lateinit var db: NotiManagerDatabase
    private lateinit var notificationRepository: NotificationRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, NotiManagerDatabase::class.java)
            .build()
        notificationDao = db.notificationDao()
        notificationMetaDao = db.notificationIntentDao()
        notificationRepository = NotificationRepository(notificationDao, notificationMetaDao)
    }

    @After
    fun tearDown() {
        db.close()
    }


    @Test
    fun testGetNotifications() = runBlocking{
        val appName = "app name"
        val title = "title"
        val notificationModelList = listOf(
            NotificationModel(
                id = 1,
                appName = appName,
                title = title,
                content = "Content 1",
                timestamp = System.currentTimeMillis(),
            ),
            NotificationModel(
                id = 2,
                appName = appName,
                title = title,
                content = "Content 2",
                timestamp = System.currentTimeMillis(),
            )
        )
        val notificationMetaList = listOf(
            NotificationMetaModel(
                notificationId = 1,
                intentActive = true,
                intentArray = "".toByteArray()
            ),
            NotificationMetaModel(
                notificationId = 2,
                intentActive = true,
                intentArray = "".toByteArray()
            )
        )

        notificationModelList.forEach { notificationRepository.insertNotification(it) }
        notificationMetaList.forEach { notificationRepository.insertNotificationMeta(it) }

        val result = notificationRepository.getNotificationTitleList(appName, title)
        assertEquals(2, result.size)
        assertEquals(title, result[0].title)
        assertEquals(title, result[1].title)
    }
}
