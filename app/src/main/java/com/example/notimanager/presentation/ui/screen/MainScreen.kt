package com.example.notimanager.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.NotificationAppListView
import com.example.notimanager.presentation.ui.component.PermissionCheck

@Composable
fun MainScreen(navController: NavController){
    PermissionCheck()
    NotificationAppListView(navController)
}