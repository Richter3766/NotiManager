package com.example.notimanager.presentation.ui.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.DateFormatterView
import com.example.notimanager.presentation.ui.component.SettingTopAppBar

@Composable
fun DateFormatterScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            SettingTopAppBar{ navController.popBackStack() }
        }
    ) { innerPadding ->
        DateFormatterView(innerPadding)
    }
}