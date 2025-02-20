package com.example.notimanager.data.utils

import android.app.PendingIntent
import android.os.Parcel
import android.util.Log

object PendingIntentHelper {
    private const val TAG = "PendingIntentHelper"

    fun savePendingIntent(pendingIntent: PendingIntent): ByteArray? {
        return try {
            val parcel = Parcel.obtain()
            PendingIntent.writePendingIntentOrNullToParcel(pendingIntent, parcel)
            val bytes = parcel.marshall()
            parcel.recycle()
            bytes
        } catch (e: Exception) {
            Log.e(TAG, "Error saving PendingIntent", e)
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
            Log.e(TAG, "Error retrieving PendingIntent", e)
            null
        }
    }
}