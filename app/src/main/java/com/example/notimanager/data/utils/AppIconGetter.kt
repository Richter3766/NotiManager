package com.example.notimanager.data.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Icon
import com.example.notimanager.common.objects.DateFormatter.toBitmap
import java.io.ByteArrayOutputStream

object AppIconGetter {
    fun convertByteArrayWithColor(context: Context, icon: Icon?, color: Int): ByteArray{
        if (icon == null) return ByteArray(0)

        // Icon을 Drawable로 변환 후 Bitmap으로 변환
        val bitmap = icon.loadDrawable(context)?.toBitmap() ?: return ByteArray(0)
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

    fun convertByteArray(context: Context, icon: Icon?): ByteArray{
        val bitmap = icon?.loadDrawable(context)?.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}