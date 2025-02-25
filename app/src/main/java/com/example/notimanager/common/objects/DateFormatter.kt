package com.example.notimanager.common.objects

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateFormatter {
    fun formatTimestamp(timestamp: Long, format: String = "relative"): String {
        return if (format == "relative") {
            val currentTime = System.currentTimeMillis()
            val timeDifference = currentTime - timestamp

            return when {
                timeDifference < TimeUnit.SECONDS.toMillis(1) -> "방금"
                timeDifference < TimeUnit.MINUTES.toMillis(1) -> "${timeDifference / 1000}초 전"
                timeDifference < TimeUnit.HOURS.toMillis(1) -> "${timeDifference / TimeUnit.MINUTES.toMillis(1)}분 전"
                timeDifference < TimeUnit.DAYS.toMillis(1) -> "${timeDifference / TimeUnit.HOURS.toMillis(1)}시간 전"
                timeDifference < TimeUnit.DAYS.toMillis(7) -> "${timeDifference / TimeUnit.DAYS.toMillis(1)}일 전"
                timeDifference < TimeUnit.DAYS.toMillis(30) -> "${timeDifference / TimeUnit.DAYS.toMillis(7)}주 전"
                timeDifference < TimeUnit.DAYS.toMillis(365) -> "${timeDifference / TimeUnit.DAYS.toMillis(30)}달 전"
                else -> "${timeDifference / TimeUnit.DAYS.toMillis(365)}년 전"
            }
        } else {
            SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault()).format(Date(timestamp))
        }
    }

    fun Drawable.toBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }
}