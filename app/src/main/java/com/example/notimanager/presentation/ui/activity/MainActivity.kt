package com.example.notimanager.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.ui.component.PermissionDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NotificationPermissionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[NotificationPermissionViewModel::class.java]
        viewModel.checkNotificationPermission()

        setContent {
            MainContent()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkNotificationPermission()
    }

    @Composable
    fun MainContent() {
        val isPermissionGranted by viewModel.isPermissionGranted.observeAsState()

        if (isPermissionGranted == false) {
            PermissionDialog.SimplePermissionDialog(
                title = "알림 권한 필요",
                message = "알림을 받기 위해 알림 권한이 필요합니다.",
                onAllow = { viewModel.requestPermission(this) },
            )
        }
    }
}
