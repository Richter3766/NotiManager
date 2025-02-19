package com.example.notimanager.data.model

import android.content.Intent
import android.os.Bundle
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class NotificationIntentModelTest: BehaviorSpec({
    Given("create should serialize extras to JSON") {
        val intent = mockk<Intent>(relaxed = true)
        val extras = mockk<Bundle>(relaxed = true)

        When("extras"){
            every { extras.keySet() } returns setOf("key1", "key2")
            every { extras.getString("key1") } returns "value1"
            every { extras.getString("key2") } returns "value2"

            every { intent.extras } returns extras
            every { intent.action } returns "ACTION_TEST"
            every { intent.`package` } returns "com.example"

            Then("결과"){
                val notificationIntentModel = NotificationIntentModel.fromIntent(intent,1L)

                val expectedJson = """{"key1":"value1","key2":"value2"}"""
                notificationIntentModel.extras shouldBe expectedJson
                notificationIntentModel.action shouldBe "ACTION_TEST"
                notificationIntentModel.packageName shouldBe "com.example"
            }
        }
    }
})