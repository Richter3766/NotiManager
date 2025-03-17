package com.notimanager.notimanager.presentation.ui.ads

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.notimanager.notimanager.R
import com.notimanager.notimanager.common.objects.DateFormatter.toBitmap
import com.notimanager.notimanager.presentation.ui.component.common.AppIconView
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun NotiNativeAds(nativeAd: NativeAd) {
    NotiNativeAdView(ad = nativeAd) { ad, view ->
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AppIconView(ad.icon?.drawable?.toBitmap())
            Column {
                // 광고 뱃지와 함께 광고 제목
                Row(){
                    Image(
                        painter = painterResource(id = R.drawable.ad_badge),
                        contentDescription = "ad badge",
                        modifier = Modifier.size(16.dp)
                    )
                    ad.headline?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                }
                ad.body?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            TextButton(
                content = { ad.callToAction?.let { Text(text = it) } },
                onClick = { view.performClick() },
            )
        }
    }
}