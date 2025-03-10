package com.example.notimanager.presentation.ui.ads

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowMetrics
import com.google.android.gms.ads.AdSize

object AdsUtil{
    // Get the ad size with screen width.
    fun getAdSize(context: Context): AdSize {
        val displayMetrics = context.resources.displayMetrics
        val adWidthPixels =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics =  (context as? Activity)?.windowManager!!.currentWindowMetrics
                windowMetrics.bounds.width()
            } else {
                displayMetrics.widthPixels
            }
        val density = displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
    }
}