package com.example.notimanager.presentation.ui.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.SettingTopAppBar
import com.example.notimanager.presentation.ui.component.SettingView

@Composable
fun SettingScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            SettingTopAppBar{ navController.popBackStack() }
        }
    ) { innerPadding ->
        SettingView(innerPadding, navController)
    }
}