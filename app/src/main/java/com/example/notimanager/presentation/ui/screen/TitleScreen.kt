package com.example.notimanager.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.component.NotificationTitleListView

@Composable
fun TitleScreen(navController: NavController, appName: String = "", title:String = ""){
    val viewModel: NotificationTitleViewModel = hiltViewModel()

    LaunchedEffect(appName, title) {
        viewModel.setArgs(appName, title)
    }

    NotificationTitleListView(navController, viewModel)

}