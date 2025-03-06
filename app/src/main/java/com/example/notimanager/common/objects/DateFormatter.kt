package com.example.notimanager.common.objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import com.example.notimanager.R
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateFormatter {
    fun formatTimestamp(context: Context, timestamp: Long, format: String = "relative"): String {
        return if (format == "relative") {
            val currentTime = System.currentTimeMillis()
            val timeDifference = currentTime - timestamp

            return when {
                timeDifference < TimeUnit.SECONDS.toMillis(1) -> context.getString(R.string.time_now)
                timeDifference < TimeUnit.MINUTES.toMillis(1) -> context.getString(R.string.time_second, timeDifference / 1000)
                timeDifference < TimeUnit.HOURS.toMillis(1) -> context.getString(R.string.time_minute, timeDifference / TimeUnit.MINUTES.toMillis(1))
                timeDifference < TimeUnit.DAYS.toMillis(1) -> context.getString(R.string.time_hour, timeDifference / TimeUnit.HOURS.toMillis(1))
                timeDifference < TimeUnit.DAYS.toMillis(7) -> context.getString(R.string.time_day, timeDifference / TimeUnit.DAYS.toMillis(1))
                timeDifference < TimeUnit.DAYS.toMillis(30) -> context.getString(R.string.time_week, timeDifference / TimeUnit.DAYS.toMillis(7))
                timeDifference < TimeUnit.DAYS.toMillis(365) -> context.getString(R.string.time_month, timeDifference / TimeUnit.DAYS.toMillis(30))
                else -> context.getString(R.string.time_year, timeDifference / TimeUnit.DAYS.toMillis(365))
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