package com.example.notimanager.data.utils

import android.content.Intent
import android.os.Parcel
import android.util.Log

object PendingIntentHelper {
    fun savePendingIntent(pendingIntent: Intent): ByteArray? {
        return try {
            val parcel = Parcel.obtain()

            pendingIntent.writeToParcel(parcel, 0)
            val bytes = parcel.marshall()
            parcel.recycle()
            bytes
        } catch (e: Exception) {
            Log.e(e.message, e.stackTraceToString())
            null
        }
    }

    fun retrievePendingIntent(byteArray: ByteArray): Intent? {
        return try {
            val parcel = Parcel.obtain()
            parcel.unmarshall(byteArray, 0, byteArray.size)
            parcel.setDataPosition(0)
            val intent = Intent.CREATOR.createFromParcel(parcel)
            parcel.recycle()
            intent
        } catch (e: Exception) {
            null
        }
    }
}