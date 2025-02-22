package com.example.notimanager.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.ui.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: NotificationPermissionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkNotificationPermission()
    }

}
