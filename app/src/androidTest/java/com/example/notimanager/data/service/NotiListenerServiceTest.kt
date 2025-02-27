import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.service.notification.StatusBarNotification
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.presentation.ui.activity.MainActivity
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.repository.NotificationRepository
import com.example.notimanager.domain.service.NotiListenerService
import com.example.notimanager.data.utils.NameGetter
import com.example.notimanager.data.utils.IntentHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotiListenerServiceTest {

    private lateinit var notiListenerService: NotiListenerService

    private val notificationRepository: NotificationRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        notiListenerService = NotiListenerService()
        notiListenerService.notificationRepository = notificationRepository
        mockkObject(NameGetter)
        mockkObject(IntentHelper)
    }

    @After
    fun tearDown() {
        unmockkObject(NameGetter)
        unmockkObject(IntentHelper)
    }

    @Test
    fun testOnNotificationPosted() = runBlocking {
        // Mocking the StatusBarNotification
        val mockNotification = mockk<Notification>(relaxed = true)
        val extras = Bundle().apply {
            putString("android.title", "Test Title")
            putString("android.text", "Test Content")
        }
        mockNotification.extras = extras

        val mockIcon = mockk<Icon>(relaxed = true)
        val uri = "content://path/to/icon"
        every { mockIcon.uri } returns Uri.parse(uri)
        every { mockIcon.toString() } returns uri
        every { mockNotification.smallIcon } returns mockIcon

        val mockSbn = mockk<StatusBarNotification>(relaxed = true).apply {
            every { notification } returns mockNotification
            every { postTime } returns System.currentTimeMillis()
            every { packageName } returns "com.example.test"
        }
        every { NameGetter.getAppName(any(), any()) } returns "Test App"

        // Create a real Intent and PendingIntent
        val realIntent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        val mockPendingIntent = PendingIntent.getActivity(
            InstrumentationRegistry.getInstrumentation().targetContext,
            0,
            realIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        mockNotification.contentIntent = mockPendingIntent

        // Mock the PendingIntentHelper
        val intentArray = "MockedByteArray".toByteArray()
        every { IntentHelper.saveIntent(realIntent) } returns intentArray

        val expectedNotificationModel = NotificationModel(
            appName = "Test App",
            title = "Test Title",
            content = "Test Content",
            timestamp = mockSbn.postTime,
        )
        coEvery { notificationRepository.insertNotification(expectedNotificationModel) } returns 1L

        val notificationMetaModel = NotificationMetaModel(
            notificationId = 1L,
            intentActive = true,
            intentArray = intentArray
        )
        coEvery { notificationRepository.insertNotificationMeta(notificationMetaModel) } returns 1L
        val notificationIconModel = NotificationIconModel(
            notificationId = 1L,
            iconBytes = "".toByteArray()
        )
        coEvery { notificationRepository.insertNotificationIcon(notificationIconModel) } returns 1L

        val appIconModel = AppIconModel(
            iconBytes = "".toByteArray(),
            notiAppName = "Test App"
        )
        coEvery { notificationRepository.insertAppIcon(appIconModel) } returns 1L

        // Call the method under test
        notiListenerService.onNotificationPosted(mockSbn)

        // Verify that the repository's insertNotification method was called
        coVerify { notificationRepository.insertNotification(expectedNotificationModel) }
        coVerify { notificationRepository.insertNotificationMeta(notificationMetaModel) }
        coVerify { notificationRepository.insertNotificationIcon(notificationIconModel) }
        coVerify { notificationRepository.insertAppIcon(appIconModel) }

        // Verify that PendingIntentHelper was called
        verify { IntentHelper.saveIntent(realIntent) }
    }
}