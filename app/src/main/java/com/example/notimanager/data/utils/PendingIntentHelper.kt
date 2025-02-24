package com.example.notimanager.data.utils

import android.app.PendingIntent
import android.os.Parcel

object PendingIntentHelper {
    fun savePendingIntent(pendingIntent: PendingIntent): ByteArray? {
        return try {
            val parcel = Parcel.obtain()
            PendingIntent.writePendingIntentOrNullToParcel(pendingIntent, parcel)
            val bytes = parcel.marshall()
            parcel.recycle()
            bytes
        } catch (e: Exception) {
            null
        }
    }

    fun retrievePendingIntent(byteArray: ByteArray): PendingIntent? {
        return try {
            val parcel = Parcel.obtain()
            parcel.unmarshall(byteArray, 0, byteArray.size)
            parcel.setDataPosition(0)
            val pendingIntent = PendingIntent.readPendingIntentOrNullFromParcel(parcel)
            parcel.recycle()
            pendingIntent
        } catch (e: Exception) {
            null // TODO: 해당 앱으로 이동하는 기본 PendingIntent로 교체
        }
    }
}