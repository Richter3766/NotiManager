package com.example.notimanager.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notimanager.R
import com.example.notimanager.domain.service.ForegroundNotiService
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel
import com.example.notimanager.presentation.ui.navigation.AppNavHost
import com.example.notimanager.presentation.ui.navigation.HandleBackPress
import com.example.notimanager.presentation.ui.theme.AppTheme
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val serviceViewModel: NotificationServicePermissionViewModel by viewModels()
    private val notificationViewModel: NotificationPermissionViewModel by viewModels()
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 언어 설정에 따라 문자열 리소스를 가져오기
        val basicTitle = getString(R.string.status_basic_title)
        val basicContent = getString(R.string.status_basic_content)

        // 화면 설정
        val appName = intent.extras?.getString("appName") ?: ""
        setContent {
            AppTheme {
                navController = rememberNavController()
                AppNavHost(navController = navController as NavHostController)

                HandleBackPress(navController as NavHostController, startRoute = "mainScreen")

                if (appName != "" && appName != "NotiManager") navController.navigate("titleScreen/$appName")
            }

        }

        // 알림 허용 권한 요청
        if (!notificationViewModel.checkNotificationPermission()){
            notificationViewModel.requestPermission(this)
        }

        // 포그라운드 서비스 실행
        val serviceIntent = Intent(this, ForegroundNotiService::class.java).apply {
            putExtra("appName", basicTitle)
            putExtra("content", basicContent)
            putExtra("isGroupSummary", true)
        }
        startService(serviceIntent)

        // 광고를 위한 백그라운드 스레드
        CoroutineScope(Dispatchers.IO).launch {
            // Google Mobile Ads SDK 초기화
            MobileAds.initialize(this@MainActivity) { }
        }
    }

    override fun onResume() {
        super.onResume()
        serviceViewModel.checkNotificationServicePermission()
    }
}
