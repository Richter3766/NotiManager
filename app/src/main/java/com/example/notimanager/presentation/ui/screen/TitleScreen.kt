package com.example.notimanager.presentation.ui.screen

import android.util.Log
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.component.list.NotificationTitleListView
import com.example.notimanager.presentation.ui.component.common.CommonTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleScreen(navController: NavController, appName: String = ""){
    val viewModel: NotificationTitleViewModel = hiltViewModel()
    val priorityViewModel: NotificationTitlePriorityViewModel = hiltViewModel()

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    var isClicked by remember { mutableStateOf(false) }
    val onRefresh: () -> Unit = {
        isRefreshing = true
        viewModel.loadNotificationTitles()
        priorityViewModel.loadNotificationTitles()
        coroutineScope.launch {
            delay(500)
            isRefreshing = false
        }
    }

    LaunchedEffect(appName) {
        viewModel.setArgs(appName)
        priorityViewModel.setArgs(appName)
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.loadNotificationTitles()
        priorityViewModel.loadNotificationTitles()
    }

    Scaffold(
        topBar = {
            CommonTopAppBar(title = appName, onBackClick = {
                if (!isClicked) {
                    isClicked = true
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            })
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier.padding(innerPadding)
        ) {
            NotificationTitleListView(navController, viewModel, priorityViewModel)
        }

    }
}