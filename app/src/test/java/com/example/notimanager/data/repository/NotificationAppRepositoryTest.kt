package com.example.notimanager.data.repository

import com.example.notimanager.data.dto.NotificationAppDto
import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.utils.TestUtils.readJsonFile
import com.example.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationAppRepositoryTest: BehaviorSpec({
    val dao = mockk<NotificationDao>()
    val appIconDao = mockk<AppIconDao>()
    val repository = NotificationAppRepository(dao, appIconDao)

    val layer = "data"
    val expectedLayer = "domain"
    Context("NotificationRepositoryDomain 테스트"){

        Given("알림 앱 우선순위 리스트가 필요한 경우 "){
            val target = "dto/NotificationAppDto.json"
            val expected = "model/NotificationApp.json"

            val notificationAppDtoList = readJsonFile(layer, target) toDtoList NotificationAppDto::class.java
            val expectedNotificationAppList = readJsonFile(expectedLayer, expected) toDtoList NotificationApp::class.java

            When("getNotificationAppList를 호출하면"){
                coEvery { dao.getNotificationAppPriorityList()  } returns notificationAppDtoList

                Then("notificationAppList를 반환해야 한다."){
                    val result = repository.getNotificationAppPriorityList()
                    result shouldBe expectedNotificationAppList
                }
            }
        }

        Given("알림 앱 비우선순위 리스트가 필요한 경우 "){
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

        Given("알림 우선순위 적용이 필요할 때 "){
            val appName = "test"

            When("setPriority를 호출하면"){
                coEvery { appIconDao.setPriority(appName, 0) } returns 1

                Then("우선순위를 적용하고, 1을 반환해야 한다."){
                    val result = repository.setAppPriority(appName, 0)
                    result shouldBe 1
                }
            }
        }

        Given("알림 우선순위 취소가 필요할 때 "){
            val appName = "test"

            When("remoevPriority를 호출하면"){
                coEvery { appIconDao.removePriority(appName) } returns 1

                Then("우선순위를 적용하고, 1을 반환해야 한다."){
                    val result = repository.removeAppPriority(appName)
                    result shouldBe 1
                }
            }
        }

    }
})