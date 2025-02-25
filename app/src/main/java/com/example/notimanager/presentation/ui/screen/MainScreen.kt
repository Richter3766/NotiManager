package com.example.notimanager.presentation.ui.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.MainTopAppBar
import com.example.notimanager.presentation.ui.component.NotificationAppListView
import com.example.notimanager.presentation.ui.component.PermissionCheck

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {
            MainTopAppBar()
        }
    ) { innerPadding ->
        NotificationAppListView(navController, innerPadding)
    }
    PermissionCheck()

}