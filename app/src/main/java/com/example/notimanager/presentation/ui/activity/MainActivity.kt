package com.example.notimanager.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notimanager.domain.service.ForegroundNotiService
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel
import com.example.notimanager.presentation.ui.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val serviceViewModel: NotificationServicePermissionViewModel by viewModels()
    private val notificationViewModel: NotificationPermissionViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            AppNavHost(navController = navController as NavHostController)
        }

        notificationViewModel.isNotificationPermissionGranted.observe(this) { isGranted ->
            if (!isGranted) {
                notificationViewModel.requestPermission(this)
            }
        }
        val serviceIntent = Intent(this, ForegroundNotiService::class.java).apply {
            putExtra("clearGroup", true)
            putExtra("appName", "NotiManager")
            putExtra("content", "실행 중입니다.")
            putExtra("isGroupSummary", true)
        }
        startService(serviceIntent)
    }

    override fun onResume() {
        super.onResume()
        serviceViewModel.checkNotificationServicePermission()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val appName = intent.extras?.getString("appName") ?: ""
        Log.i("앱 이름", appName)
        if (appName == ""){
            navController.navigate("mainScreen")
        }

        navController.navigate("titleScreen/$appName")
    }
}
