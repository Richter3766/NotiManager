package com.example.notimanager.domain.usecase

import android.content.Intent
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationUseCaseTest: BehaviorSpec({
    val notificationRepositoryInterface = mockk<NotificationRepositoryInterface>()

    val intent = mockk<Intent>(relaxed = true)

    Context("NotificationUseCase 테스트"){
        Given("notification List가 필요할 때"){
            val notificationList = listOf(
                Notification(
                    packageName = "com.example",
                    title = "Test Notification 1",
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                    intent = intent
                ),
                Notification(
                    packageName = "com.example",
                    title = "Test Notification 2",
                    content = "Content 2",
                    timestamp = System.currentTimeMillis(),
                    intent = intent
                )
            )

            When("getAllNotifications를 실행하면"){
                coEvery { notificationRepositoryInterface.getAllNotifications() } returns notificationList

                Then("해당 리스트를 반환받아야 한다."){
                    val result = notificationRepositoryInterface.getAllNotifications()
                    result.size shouldBe 2
                    result[0].title shouldBe "Test Notification 1"
                    result[1].title shouldBe "Test Notification 2"
                }
            }
        }
    }
})