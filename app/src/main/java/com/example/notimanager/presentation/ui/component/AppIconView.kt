package com.example.notimanager.presentation.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun AppIconView(appIcon: Bitmap?) {
    if (appIcon != null) {
        Image(
            bitmap = appIcon.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
    else{
        Image(
            bitmap = Bitmap.createBitmap(100, 10, Bitmap.Config.ARGB_8888).asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}