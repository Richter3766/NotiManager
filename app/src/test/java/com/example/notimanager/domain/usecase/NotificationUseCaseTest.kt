package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationUseCaseTest: BehaviorSpec({
    val repository = mockk<NotificationRepositoryInterface>()
    val useCase = NotificationUseCase(repository)

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
                ),
                NotificationApp(
                    appName = appName,
                    title = title,
                    content = "Content 2",
                    timestamp = System.currentTimeMillis(),
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

        Given("getNotificationTitleList 경우 "){
            val notificationTitleList = listOf(
                NotificationTitle(
                    title = title,
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                ),
                NotificationTitle(
                    title = title,
                    content = "Content 2",
                    timestamp = System.currentTimeMillis(),
                )
            )

            When("호출 시"){
                coEvery { repository.getNotificationTitleList(appName, title)  } returns notificationTitleList

                Then("notificationAppList를 반환해야 한다."){
                    val result = useCase.getNotificationTitleList(appName, title)
                    result shouldBe notificationTitleList
                }
            }
        }

        Given("getNotificationList 경우 "){
            val notificationList = listOf(
                Notification(
                    title = title,
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                    intent = null,
                    intentActive = true,
                ),
                Notification(
                    title = title,
                    content = "Content 1",
                    timestamp = System.currentTimeMillis(),
                    intent = null,
                    intentActive = true,
                )
            )

            When("호출 시"){
                coEvery { repository.getNotificationList(appName, title)  } returns notificationList

                Then("notificationAppList를 반환해야 한다."){
                    val result = useCase.getNotificationList(appName, title)
                    result shouldBe notificationList
                }
            }
        }
    }
})