package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.repository.NotificationRepositoryDomainInterface
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationUseCaseTest: BehaviorSpec({
    val repository = mockk<NotificationRepositoryDomainInterface>()
    val useCase = NotificationAppUseCase(repository)

    val appName = "app name"
    val title = "title"
    Context("NotificationUseCase 테스트"){
        Given("getNotificationAppList 경우 "){
            val notificationAppList = listOf(
                NotificationApp(
                    appName = appName,
                    title = title,
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                    appIconResId = "",
                ),
                NotificationApp(
                    appName = appName,
                    title = title,
                    content = "Content 2",
                    timestamp = System.currentTimeMillis(),
                    appIconResId = "",
                )
            )

            When("호출 시"){
                coEvery { repository.getNotificationAppList()  } returns notificationAppList

                Then("notificationAppList를 반환해야 한다."){
                    val result = useCase.getNotificationAppList()
                    result shouldBe notificationAppList
                }
            }
        }
    }
})