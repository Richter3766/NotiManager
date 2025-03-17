package com.notimanager.notimanager.common.objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.notimanager.notimanager.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateFormatter {
    fun formatTimestamp(context: Context, timestamp: Long): String {
        val sharedPreferences = context.getSharedPreferences("date_format", Context.MODE_PRIVATE)
        val format = sharedPreferences.getString("date_format", "relative") ?: "relative"

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
            formatDate(timestamp)
        }
    }

    private fun formatDate(timestamp: Long): String {
        val currentLocale = Locale.getDefault()

        // 로케일에 따라 포맷터 설정
        val formatter: DateTimeFormatter = if (currentLocale.language == "ko") {
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분", currentLocale)
        } else {
            DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm a", currentLocale)
        }

        // 타임스탬프를 Instant로 변환
        val instant = Instant.ofEpochMilli(timestamp)

        // 포맷터를 사용하여 날짜 포맷팅
        return formatter.withZone(ZoneId.systemDefault()).format(instant)
    }

    fun Drawable.toBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }
}