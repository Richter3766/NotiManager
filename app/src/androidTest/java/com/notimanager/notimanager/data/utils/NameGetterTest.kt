package com.notimanager.notimanager.data.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.UserHandle
import android.service.notification.StatusBarNotification
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.notimanager.notimanager.domain.utils.NameGetter
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameGetterTest {

    private lateinit var mockContext: Context
    private lateinit var mockPackageManager: PackageManager
    private lateinit var mockSbn: StatusBarNotification
    private lateinit var mockApplicationInfo: ApplicationInfo
    private val testPackageName = "com.example.testapp"
    private val testAppName = "Test App"

    @Before
    fun setUp() {
        mockContext = mockk(relaxed = true)
        mockPackageManager = mockk(relaxed = true)
        mockSbn = mockk(relaxed = true)
        mockApplicationInfo = mockk(relaxed = true)

        // Mocking the packageManager
        every { mockContext.packageManager } returns mockPackageManager
        every { mockSbn.packageName } returns testPackageName
        every { mockSbn.user } returns mockk<UserHandle>(relaxed = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetAppName_success() {
        // Mocking the successful scenario
        every { mockPackageManager.getApplicationInfo(testPackageName, 0) } returns mockApplicationInfo
        every { mockPackageManager.getApplicationLabel(mockApplicationInfo) } returns testAppName

        // Call the method under test
        val appName = NameGetter.getAppName(mockContext, mockSbn)

        // Verify the result
        Assert.assertEquals(testAppName, appName)
    }

    @Test
    fun testGetAppName_nameNotFoundException() {
        // Mocking the NameNotFoundException scenario
        every { mockPackageManager.getApplicationInfo(testPackageName, 0) } throws PackageManager.NameNotFoundException()

        // Call the method under test
        val appName = NameGetter.getAppName(mockContext, mockSbn)

        // Verify the result
        Assert.assertEquals("Unknown App", appName)
    }
}