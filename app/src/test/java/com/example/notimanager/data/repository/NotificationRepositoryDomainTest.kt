package com.example.notimanager.data.repository

import com.example.notimanager.data.dto.NotificationAppDto
import com.example.notimanager.data.dto.NotificationDto
import com.example.notimanager.data.dto.NotificationTitleDto
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.utils.TestUtils.readJsonFile
import com.example.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationRepositoryDomainTest: BehaviorSpec({
    val dao = mockk<NotificationDao>()
    val repository = NotificationRepositoryDomain(dao)

    val layer = "data"
    val expectedLayer = "domain"
    Context("NotificationRepositoryDomain 테스트"){

        Given("알림 앱 리스트가 필요한 경우 "){
            val target = "dto/NotificationAppDto.json"
            val expected = "model/NotificationApp.json"

            val notificationAppDtoList = readJsonFile(layer, target) toDtoList NotificationAppDto::class.java
            val expectedNotificationAppList = readJsonFile(expectedLayer, expected) toDtoList NotificationApp::class.java

            When("getNotificationAppList를 호출하면"){
                coEvery { dao.getNotificationAppList()  } returns notificationAppDtoList

                Then("notificationAppList를 반환해야 한다."){
                    val result = repository.getNotificationAppList()
                    result shouldBe expectedNotificationAppList
                }
            }
        }

        Given("앱의 알림 제목 리스트가 필요한 경우"){
            val target = "dto/NotificationTitleDto.json"
            val expected = "model/NotificationTitle.json"

            val notificationTitleDtoList = readJsonFile(layer, target) toDtoList NotificationTitleDto::class.java
            val expectedNotificationTitleList = readJsonFile(expectedLayer, expected) toDtoList NotificationTitle::class.java

            When("getNotificationTitleList를 호출하면"){
                coEvery { dao.getNotificationTitleList("", "")  } returns notificationTitleDtoList

                Then("notificationTitleList를 반환해야 한다."){
                    val result = repository.getNotificationTitleList("", "")
                    result shouldBe expectedNotificationTitleList
                }
            }
        }

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