package com.example.notimanager.data.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import com.example.notimanager.common.objects.DateFormatter.toBitmap
import java.io.ByteArrayOutputStream

object AppIconGetter {
    fun convertByteArray(context: Context, icon: Icon?): ByteArray{
        val bitmap = icon?.loadDrawable(context)?.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}