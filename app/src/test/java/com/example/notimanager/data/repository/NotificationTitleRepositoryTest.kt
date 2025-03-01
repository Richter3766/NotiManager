package com.example.notimanager.data.repository

import com.example.notimanager.data.dto.NotificationTitleDto
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.utils.TestUtils.readJsonFile
import com.example.notimanager.utils.TestUtils.toDtoList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationTitleRepositoryTest: BehaviorSpec({
    val dao = mockk<NotificationDao>()
    val notificationIconDao = mockk<NotificationIconDao>()
    val repository = NotificationTitleRepository(dao, notificationIconDao)

    val layer = "data"
    val expectedLayer = "domain"
    Context("NotificationRepositoryDomain 테스트"){

        Given("앱의 알림 제목 우선순위 리스트가 필요한 경우"){
            val target = "dto/NotificationTitleDto.json"
            val expected = "model/NotificationTitle.json"

            val notificationTitleDtoList = readJsonFile(layer, target) toDtoList NotificationTitleDto::class.java
            val expectedNotificationTitleList = readJsonFile(expectedLayer, expected) toDtoList NotificationTitle::class.java

            When("getNotificationTitlePriorityList를 호출하면"){
                coEvery { dao.getNotificationTitleList("", true)  } returns notificationTitleDtoList

                Then("notificationTitleList를 반환해야 한다."){
                    val result = repository.getNotificationTitlePriorityList("")
                    result shouldBe expectedNotificationTitleList
                }
            }
        }

        Given("앱의 알림 제목 비우선순위 리스트가 필요한 경우"){
            val target = "dto/NotificationTitleDto.json"
            val expected = "model/NotificationTitle.json"

            val notificationTitleDtoList = readJsonFile(layer, target) toDtoList NotificationTitleDto::class.java
            val expectedNotificationTitleList = readJsonFile(expectedLayer, expected) toDtoList NotificationTitle::class.java

            When("getNotificationTitleList를 호출하면"){
                coEvery { dao.getNotificationTitleList("", false)  } returns notificationTitleDtoList

                Then("notificationTitleList를 반환해야 한다."){
                    val result = repository.getNotificationTitleList("")
                    result shouldBe expectedNotificationTitleList
                }
            }
        }

        Given("알림 제목 우선순위 적용이 필요할 때 "){
            val id = 1L

            When("setPriority를 호출하면"){
                coEvery { notificationIconDao.setPriority(id, 0) } returns 1

                Then("우선순위를 적용하고, 1을 반환해야 한다."){
                    val result = repository.setTitlePriority(id, 0)
                    result shouldBe 1
                }
            }
        }

        Given("알림 우선순위 취소가 필요할 때 "){
            val id = 1L

            When("remoevPriority를 호출하면"){
                coEvery { notificationIconDao.removePriority(id) } returns 1

                Then("우선순위를 적용하고, 1을 반환해야 한다."){
                    val result = repository.removeTitlePriority(id)
                    result shouldBe 1
                }
            }
        }
    }


})