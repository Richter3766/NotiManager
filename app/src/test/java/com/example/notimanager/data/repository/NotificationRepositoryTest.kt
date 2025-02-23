package com.example.notimanager.data.repository

import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.utils.TestUtils.readJsonFile
import com.example.notimanager.utils.TestUtils.toDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class NotificationRepositoryTest: BehaviorSpec({
    val notificationDao: NotificationDao = mockk()
    val notificationMetaDao: NotificationMetaDao = mockk()
    val notificationIconDao: NotificationIconDao = mockk()
    val appIconDao: AppIconDao = mockk()
    val repository = NotificationRepository(
        notificationDao,
        notificationMetaDao,
        notificationIconDao,
        appIconDao
    )

    Context("NotificationRepository 테스트"){
        val layer = "data/model"

        Given("알림 모델 저장 테스트"){
            val target = "NotificationModel.json"
            val notificationModel = readJsonFile(layer, target) toDto NotificationModel::class.java

            When("요청이 들어오면"){
                coEvery { repository.insertNotification(notificationModel) } returns 1

                Then("삽입한 후 id를 리턴한다."){
                    val result = repository.insertNotification(notificationModel)
                    result shouldBe 1
                }
            }
        }
        Given("알림 메타 데이터 모델 저장 테스트"){
            val target = "NotificationMetaModel.json"
            val notificationMetaModel = readJsonFile(layer, target) toDto NotificationMetaModel::class.java

            When("요청이 들어오면"){
                coEvery { repository.insertNotificationMeta(notificationMetaModel) } returns 1

                Then("삽입한 후 id를 리턴한다."){
                    val result = repository.insertNotificationMeta(notificationMetaModel)
                    result shouldBe 1
                }
            }
        }

        Given("알림 아이콘 모델 저장 테스트"){
            val target = "NotificationIconModel.json"
            val notificationIconModel = readJsonFile(layer, target) toDto NotificationIconModel::class.java

            When("요청이 들어오면"){
                coEvery { repository.insertNotificationIcon(notificationIconModel) } returns 1

                Then("삽입한 후 id를 리턴한다."){
                    val result = repository.insertNotificationIcon(notificationIconModel)
                    result shouldBe 1
                }
            }
        }

        Given("앱 아이콘 모델 저장 테스트"){
            val target = "AppIconModel.json"
            val appIconModel = readJsonFile(layer, target) toDto AppIconModel::class.java

            When("요청이 들어오면"){
                coEvery { repository.insertAppIcon(appIconModel) } returns 1

                Then("삽입한 후 id를 리턴한다."){
                    val result = repository.insertAppIcon(appIconModel)
                    result shouldBe 1
                }
            }
        }
    }
})