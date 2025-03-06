package com.example.notimanager.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notimanager.R
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

        // 언어 변경에 따라 문자열 리소스를 가져오기
        val basicTitle = getString(R.string.status_basic_title)
        val basicContent = getString(R.string.status_basic_content)

        val appName = intent.extras?.getString("appName") ?: ""
        setContent {
            navController = rememberNavController()
            AppNavHost(navController = navController as NavHostController)
            if (appName != "" && appName != "NotiManager") navController.navigate("titleScreen/$appName")
        }

        notificationViewModel.isNotificationPermissionGranted.observe(this) { isGranted ->
            if (!isGranted) {
                notificationViewModel.requestPermission(this)
            }
        }
        val serviceIntent = Intent(this, ForegroundNotiService::class.java).apply {
            putExtra("clearGroup", true)
            putExtra("appName", basicTitle)
            putExtra("content", basicContent)
            putExtra("isGroupSummary", true)
        }
        startService(serviceIntent)
    }

    override fun onResume() {
        super.onResume()
        serviceViewModel.checkNotificationServicePermission()
    }
}
