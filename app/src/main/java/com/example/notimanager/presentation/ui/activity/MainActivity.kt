package com.example.notimanager.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel
import com.example.notimanager.presentation.ui.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val serviceViewModel: NotificationServicePermissionViewModel by viewModels()
    private val notificationViewModel: NotificationPermissionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavHost(navController = rememberNavController())
        }

        notificationViewModel.isNotificationPermissionGranted.observe(this) { isGranted ->
            if (!isGranted) {
                notificationViewModel.requestPermission(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        serviceViewModel.checkNotificationServicePermission()
    }
}
