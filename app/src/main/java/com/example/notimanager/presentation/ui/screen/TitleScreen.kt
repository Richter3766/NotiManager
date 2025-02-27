package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.component.NotificationTitleListView
import com.example.notimanager.presentation.ui.component.TitleTopAppBar

@Composable
fun TitleScreen(navController: NavController, appName: String = "", title:String = ""){
    val viewModel: NotificationTitleViewModel = hiltViewModel()
    val priorityViewModel: NotificationTitlePriorityViewModel = hiltViewModel()

    LaunchedEffect(appName, title) {
        viewModel.setArgs(appName, title)
        priorityViewModel.setArgs(appName, title)
    }
    Scaffold(
        topBar = {
            TitleTopAppBar(title = title, onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        NotificationTitleListView(navController, innerPadding, viewModel, priorityViewModel)
    }
}