package com.example.notimanager.domain.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import com.example.notimanager.common.objects.DateFormatter.toBitmap
import java.io.ByteArrayOutputStream

object AppIconGetter {
    fun convertByteArrayWithColor(icon: Drawable?, color: Int): ByteArray?{
        if (icon == null) return null

        // Icon을 Drawable로 변환 후 Bitmap으로 변환
        val bitmap = icon.toBitmap()
        val bitmapConfig = bitmap.config ?: Bitmap.Config.ARGB_8888

        // 색상 필터 적용
        val coloredBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmapConfig)
        val canvas = Canvas(coloredBitmap)
        val paint = Paint().apply {
            isAntiAlias = true
            colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }

        // 원래 비트맵을 캔버스에 그리기
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        // 색상 필터를 적용하여 다시 그리기
        canvas.drawBitmap(coloredBitmap, 0f, 0f, paint)

        val stream = ByteArrayOutputStream()
        coloredBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun convertByteArray(icon: Drawable?): ByteArray{
        val bitmap = icon?.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun getAppIcon(context: Context, packageName: String): Drawable? {
        return try {
            val packageManager: PackageManager = context.packageManager
            val applicationInfo: ApplicationInfo = packageManager.getApplicationInfo(packageName, 0)
            packageManager.getApplicationIcon(applicationInfo)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}