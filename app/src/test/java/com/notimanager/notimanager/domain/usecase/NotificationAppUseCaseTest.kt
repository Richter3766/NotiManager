package com.notimanager.notimanager.domain.usecase

import com.notimanager.notimanager.domain.model.NotificationApp
import com.notimanager.notimanager.domain.repository.NotificationAppRepositoryInterface
import com.notimanager.notimanager.utils.TestUtils.readJsonFile
import com.notimanager.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationAppUseCaseTest: BehaviorSpec({
    val repository = mockk<NotificationAppRepositoryInterface>()
    val useCase = NotificationAppUseCase(repository)

    val layer = "domain"
    val target = "model/NotificationApp.json"
    Context("NotificationAppUseCase 테스트"){
        Given("알림 앱 리스트가 필요한 경우 "){
            val notificationAppList = readJsonFile(layer, target) toDtoList NotificationApp::class.java

            When("getNotificationAppList를 호출하면"){
                coEvery { repository.getNotificationAppList()  } returns notificationAppList

                Then("notificationAppList를 반환해야 한다."){
                    val result = useCase.getNotificationAppList()
                    result shouldBe notificationAppList
                }
            }
        }
    }
})