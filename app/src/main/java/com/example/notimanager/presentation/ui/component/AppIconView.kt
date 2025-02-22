package com.example.notimanager.presentation.ui.component

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notimanager.R
import com.example.notimanager.common.objects.DateFormatter.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppIconView(packageName: String) {
    val context = LocalContext.current
    var appIconDrawable by remember { mutableStateOf<Drawable?>(null) }

    LaunchedEffect(packageName) {
        appIconDrawable = getAppIcon(packageName, context)
    }

    if (appIconDrawable != null) {
        Image(
            bitmap = appIconDrawable!!.toBitmap().asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    } else {
        // null일 경우 기본 이미지 표시
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }

}


suspend fun getAppIcon(packageName: String, context: Context): Drawable? {
    return withContext(Dispatchers.IO) {
        try {
            val packageManager: PackageManager = context.packageManager
            val applicationInfo: ApplicationInfo = packageManager.getApplicationInfo(packageName, 0)
            packageManager.getApplicationIcon(applicationInfo)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }
}