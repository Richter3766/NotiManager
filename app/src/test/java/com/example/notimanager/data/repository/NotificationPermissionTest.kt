package com.example.notimanager.data.repository

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.slot
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic


class NotificationPermissionRepositoryTest : BehaviorSpec({
    val mockContext = mockk<Context>(relaxed = true)
    val repository = NotificationPermissionRepository(mockContext)
    val pkgName = "com.example.notiManager"

    Given("알림 서비스가 활성화된 경우") {
        val mockContentResolver = mockk<ContentResolver>(relaxed = true)
        val enabledListeners = "$pkgName, com.other.app"

        every { mockContext.contentResolver } returns mockContentResolver
        every { mockContext.packageName } returns pkgName

        mockkStatic(Settings.Secure::class).apply {
            every{ Settings.Secure.getString(mockContext.contentResolver, "enabled_notification_listeners" )} returns enabledListeners
        }

        When("isNotificationServiceEnabled 호출 시") {
            val result = repository.isNotificationServiceEnabled()

            Then("결과는 true 이어야 한다") {
                result shouldBe true
            }
        }
    }

    Given("알림 서비스가 비활성화된 경우") {
        val mockContentResolver = mockk<ContentResolver>(relaxed = true)
        val enabledListeners = "com.other.app"

        When("isNotificationServiceEnabled 호출 시") {
            every { mockContext.contentResolver } returns mockContentResolver
            every { mockContext.packageName } returns pkgName

            mockkStatic(Settings.Secure::class).apply {
                every{ Settings.Secure.getString(mockContext.contentResolver, "enabled_notification_listeners" )} returns enabledListeners
            }

            Then("결과는 false 이어야 한다") {
                val result = repository.isNotificationServiceEnabled()
                result shouldBe false
            }
        }

        When("flat이 null일 때"){
            every { mockContext.contentResolver } returns mockContentResolver
            every { mockContext.packageName } returns pkgName

            Then("결과는 false 이어야 한다."){
                val result = repository.isNotificationServiceEnabled()
                result shouldBe false
            }
        }
    }

    Given("requestPermission 호출 시") {
        val intentSlot = slot<Intent>()

        When("requestPermission 호출") {
            repository.requestPermission()

            Then("알림 설정 화면을 여는 Intent가 시작되어야 한다") {
                verify { mockContext.startActivity(capture(intentSlot)) }
            }
        }
    }
})
