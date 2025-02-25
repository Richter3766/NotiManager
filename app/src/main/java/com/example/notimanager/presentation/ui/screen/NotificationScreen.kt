package com.example.notimanager.presentation.ui.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationViewModel
import com.example.notimanager.presentation.ui.component.NotificationListView
import com.example.notimanager.presentation.ui.component.NotificationTopAppBar

@Composable
fun NotificationScreen(navController: NavController, appName: String = "", title:String = ""){
    val viewModel: NotificationViewModel = hiltViewModel()

    LaunchedEffect(appName, title) {
        viewModel.setArgs(appName, title)
    }
    Scaffold(
        topBar = {
            NotificationTopAppBar(title = title, onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        NotificationListView(innerPadding, viewModel)
    }
}