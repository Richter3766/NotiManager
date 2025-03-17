package com.notimanager.notimanager.presentation.stateholder.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DateFormatterViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel() {
    private val prefs = "date_format"
    private val sharedPreferences = appContext.getSharedPreferences(prefs, Context.MODE_PRIVATE)

    val relativeTime = "relative"
    val absoluteTime = "absolute"

    fun setDateFormat(format: String) {
        if (format == relativeTime || format == absoluteTime)
            sharedPreferences.edit().putString(prefs, format).apply()
    }

    fun getDateFormat(): String {
        return sharedPreferences.getString(prefs, relativeTime) ?: relativeTime
    }
}
