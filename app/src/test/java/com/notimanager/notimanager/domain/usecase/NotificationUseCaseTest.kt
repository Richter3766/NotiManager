package com.notimanager.notimanager.domain.usecase

import com.notimanager.notimanager.domain.model.Notification
import com.notimanager.notimanager.domain.repository.NotificationDomainRepositoryInterface
import com.notimanager.notimanager.utils.TestUtils.readJsonFile
import com.notimanager.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationUseCaseTest: BehaviorSpec({
    val repository = mockk<NotificationDomainRepositoryInterface>()
    val useCase = NotificationUseCase(repository)

    val layer = "domain"
    val target = "model/Notification.json"
    Context("NotificationUseCase 테스트"){
        Given("특정 앱의 알림 리스트가 필요한 경우 "){
            val notificationList = readJsonFile(layer, target) toDtoList Notification::class.java

            When("getNotificationAppList를 호출하면"){
                coEvery { repository.getNotificationList(any(), any())  } returns notificationList

                Then("notificationAppList를 반환해야 한다."){
                    val result = useCase.getNotificationList("", "")
                    result shouldBe notificationList
                }
            }
        }
    }
})