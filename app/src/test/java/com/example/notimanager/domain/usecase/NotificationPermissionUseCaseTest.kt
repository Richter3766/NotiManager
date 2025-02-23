package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class NotificationPermissionUseCaseTest : BehaviorSpec({
    val repository = mockk<NotificationPermissionRepositoryInterface>()
    val useCase = NotificationPermissionUseCase(repository)

    afterTest {
        clearMocks(repository)
    }

    Given("알림 권한이 필요한 상황") {
        When("알림 서비스가 활성화되어 있지 않으면") {
            every { repository.isNotificationServiceEnabled() } returns false

            Then("알림 서비스가 활성화되어 있지 않음을 반환해야 한다") {
                useCase.isNotificationServiceEnabled() shouldBe false
            }
        }

        When("알림 서비스가 비활성화되어 있다면") {
            every { repository.isNotificationServiceEnabled() } returns false
            every { repository.requestPermission() } returns Unit

            Then("권한 요청을 해야 한다") {
                useCase.requestPermission()
                verify { repository.requestPermission() }
            }
        }
    }

    Given("알림 권한이 부여된 상황"){
        When("알림 서비스가 활성화되어 있다면") {
            every { repository.isNotificationServiceEnabled() } returns true

            Then("알림 서비스가 활성화되어 있음을 반환해야 한다") {
                useCase.isNotificationServiceEnabled() shouldBe true
            }
        }

        When("알림 서비스가 이미 활성화되어 있다면") {
            every { repository.isNotificationServiceEnabled() } returns true

            Then("권한 요청을 하지 않아야 한다") {
                useCase.requestPermission()
                verify(exactly = 0) { repository.requestPermission() }
            }
        }
    }
})
