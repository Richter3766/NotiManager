package com.example.notimanager.presentation.ui.ads

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun NotiNativeAds(nativeAd: NativeAd) {
    NotiNativeAdView(ad = nativeAd) { ad, view ->
        //Style it whatever you want
        Log.i("ad 정보", ad.toString())
        Log.i("ad 정보", ad.headline.toString())
        Column {
            ad.headline?.let { Text(text = it) }
            TextButton(
                content = { ad.callToAction?.let { Text(text = it) } },
                onClick = { view.performClick() },
            )
        }
    }
}