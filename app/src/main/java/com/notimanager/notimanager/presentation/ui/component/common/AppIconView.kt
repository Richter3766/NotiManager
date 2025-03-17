package com.notimanager.notimanager.presentation.ui.component.common

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppIconView(appIcon: Bitmap?, size: Dp = 50.dp) {
    if (appIcon != null) {
        Image(
            bitmap = appIcon.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
    else{
        Image(
            bitmap = Bitmap.createBitmap(100, 10, Bitmap.Config.ARGB_8888).asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
}