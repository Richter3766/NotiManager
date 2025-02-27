package com.example.notimanager.data.repository

import com.example.notimanager.data.dto.NotificationDto
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.utils.TestUtils.readJsonFile
import com.example.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationRepositoryDomainTest: BehaviorSpec({
    val dao = mockk<NotificationDao>()
    val repository = NotificationDomainRepository(dao)

    val layer = "data"
    val expectedLayer = "domain"
    Context("NotificationRepositoryDomain 테스트"){

        Given("앱의 알림 리스트가 필요한 경우"){
            val target = "dto/NotificationDto.json"
            val expected = "model/Notification.json"

            val notificationDtoList = readJsonFile(layer, target) toDtoList NotificationDto::class.java
            val expectedNotificationList = readJsonFile(expectedLayer, expected) toDtoList Notification::class.java

            When("getNotificationList를 호출하면"){
                coEvery { dao.getNotificationList("", "")  } returns notificationDtoList

                Then("notificationList를 반환해야 한다."){
                    val result = repository.getNotificationList("", "")
                    result shouldBe expectedNotificationList
                }
            }
        }
    }
})