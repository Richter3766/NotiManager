package com.example.notimanager.presentation.ui.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.ui.unit.dp

@Composable
fun IconFromUri(iconUri: String) {
    val uri = Uri.parse(iconUri)

    Image(
        painter = rememberAsyncImagePainter(uri),
        contentDescription = null,
        modifier = Modifier.size(48.dp)
        
    )
}