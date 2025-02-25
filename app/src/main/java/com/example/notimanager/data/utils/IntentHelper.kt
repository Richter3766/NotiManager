package com.example.notimanager.data.utils

import android.content.Intent
import android.os.Parcel

object IntentHelper {
    fun saveIntent(pendingIntent: Intent): ByteArray? {
        return try {
            val parcel = Parcel.obtain()

            pendingIntent.writeToParcel(parcel, 0)
            val bytes = parcel.marshall()
            parcel.recycle()
            bytes
        } catch (e: Exception) {
            null
        }
    }

    fun retrieveIntent(byteArray: ByteArray): Intent? {
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