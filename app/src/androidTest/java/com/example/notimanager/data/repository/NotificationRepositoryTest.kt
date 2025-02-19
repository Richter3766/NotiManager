package com.example.notimanager.data.repository

import android.content.Context
import android.content.Intent
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIntentDao
import com.example.notimanager.data.source.local.database.NotiManagerDatabase
import com.example.notimanager.data.utils.ModelConverter.toDomain
import com.example.notimanager.domain.model.Notification
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationRepositoryTest {

    private lateinit var notificationDao: NotificationDao
    private lateinit var notificationIntentDao: NotificationIntentDao
    private lateinit var db: NotiManagerDatabase
    private lateinit var notificationRepository: NotificationRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, NotiManagerDatabase::class.java)
            .build()
        notificationDao = db.notificationDao()
        notificationIntentDao = db.notificationIntentDao()
        notificationRepository = NotificationRepository(notificationDao, notificationIntentDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testAddNotification() = runBlocking {
        val testNotification = Notification(
            packageName = "com.example.test",
            title = "test",
            content = "content",
            timestamp = 1111L,
            intent = Intent().apply {
                action = "ACTION_TEST"
                `package` = "com.example.test"
            }
        )

        notificationRepository.addNotification(testNotification)

        val result = notificationRepository.getAllNotifications()
        assertEquals(1, result.size)
    }

    @Test
    fun testGetNotifications() = runBlocking{
        val notificationModelList = listOf(
            NotificationModel(
                id = 1,
                packageName = "com.example",
                title = "Test Notification 1",
                content = "Content 1",
                timestamp = System.currentTimeMillis()
            ),
            NotificationModel(
                id = 2,
                packageName = "com.example",
                title = "Test Notification 2",
                content = "Content 2",
                timestamp = System.currentTimeMillis()
            )
        )

        notificationModelList.forEach { notificationRepository.addNotification(it.toDomain(Intent())) }

        val result = notificationRepository.getAllNotifications()
        assertEquals(2, result.size)
        assertEquals("Test Notification 1", result[0].title)
        assertEquals("Test Notification 2", result[1].title)
    }
}
