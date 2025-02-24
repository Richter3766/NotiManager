package com.example.notimanager.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationViewModel
import com.example.notimanager.presentation.ui.component.NotificationListView

@Composable
fun NotificationScreen(appName: String = "", title:String = ""){
    val viewModel: NotificationViewModel = hiltViewModel()

    LaunchedEffect(appName, title) {
        viewModel.setArgs(appName, title)
    }

    NotificationListView(viewModel)

}