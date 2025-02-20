package com.example.notimanager.data.utils

import android.app.PendingIntent
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notimanager.MainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PendingIntentHelperTest {

    @Test
    fun testSaveAndRetrievePendingIntent() {
        // 1. Create a Real PendingIntent
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, MainActivity::class.java)
        val originalPendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 2. Serialize
        val byteArray = PendingIntentHelper.savePendingIntent(originalPendingIntent)
            ?: throw AssertionError("Serialization failed: byteArray is null")

        // Check if serialization was successful

        // 3. Deserialize
        val retrievedPendingIntent = PendingIntentHelper.retrievePendingIntent(byteArray)
            ?: throw AssertionError("Deserialization failed: retrievedPendingIntent is null")

        // 4. Compare
        // Check if deserialization was successful

        // Check if the retrieved PendingIntent is equal to the original one
        Assert.assertEquals(originalPendingIntent.creatorPackage, retrievedPendingIntent.creatorPackage)
        Assert.assertEquals(originalPendingIntent.isActivity, retrievedPendingIntent.isActivity)
        Assert.assertEquals(originalPendingIntent.isBroadcast, retrievedPendingIntent.isBroadcast)
        Assert.assertEquals(originalPendingIntent.isForegroundService, retrievedPendingIntent.isForegroundService)
        Assert.assertEquals(originalPendingIntent.isService, retrievedPendingIntent.isService)
    }
}