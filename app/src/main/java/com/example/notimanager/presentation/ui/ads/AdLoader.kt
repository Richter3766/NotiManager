package com.example.notimanager.presentation.ui.ads

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

fun createNativeAdLoader(context: Context, onAdLoaded: (NativeAd) -> Unit, onAdFailedToLoad: (LoadAdError) -> Unit): AdLoader {
    return AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
        .forNativeAd { ad: NativeAd ->
            onAdLoaded(ad) // 광고가 로드되었을 때 호출
        }
        .withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                onAdFailedToLoad(adError) // 광고 로드 실패 시 호출
            }
        })
        .withNativeAdOptions(NativeAdOptions.Builder().build())
        .build()
}
