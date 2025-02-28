package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.component.NotificationTitleListView
import com.example.notimanager.presentation.ui.component.TitleTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleScreen(navController: NavController, appName: String = "", title:String = ""){
    val viewModel: NotificationTitleViewModel = hiltViewModel()
    val priorityViewModel: NotificationTitlePriorityViewModel = hiltViewModel()

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                viewModel.loadNotificationTitles()
                priorityViewModel.loadNotificationTitles()
                coroutineScope.launch {
                    delay(2000)
                    isRefreshing = false
                }
            },
            modifier = Modifier
                .padding(innerPadding)
        ) {
            NotificationTitleListView(navController, viewModel, priorityViewModel)
        }

    }
}