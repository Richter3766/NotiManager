package com.notimanager.notimanager.domain.usecase

import com.notimanager.notimanager.domain.model.NotificationTitle
import com.notimanager.notimanager.domain.repository.NotificationTitleRepositoryInterface
import com.notimanager.notimanager.utils.TestUtils.readJsonFile
import com.notimanager.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationTitleUseCaseTest: BehaviorSpec({
    val repository = mockk<NotificationTitleRepositoryInterface>()
    val useCase = NotificationTitleUseCase(repository)

    val layer = "domain"
    val target = "model/NotificationTitle.json"
    Context("NotificationTitleUseCase 테스트"){
        Given("특정 앱의 알림 제목 리스트가 필요한 경우 "){
            val notificationTitleList = readJsonFile(layer, target) toDtoList NotificationTitle::class.java

            When("getNotificationTitleList를 호출하면"){
                coEvery { repository.getNotificationTitleList(any())  } returns notificationTitleList

                Then("notificationTitleList를 반환해야 한다."){
                    val result = useCase.getNotificationTitleList("")
                    result shouldBe notificationTitleList
                }
            }
        }
    }
})