package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppPriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel
import com.example.notimanager.presentation.ui.component.MainTopAppBar
import com.example.notimanager.presentation.ui.component.NotificationAppListView
import com.example.notimanager.presentation.ui.component.PermissionCheck
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
) {
    val viewModel: NotificationAppViewModel = hiltViewModel()
    val priorityViewModel: NotificationAppPriorityViewModel = hiltViewModel()

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val onRefresh: () -> Unit = {
        isRefreshing = true
        viewModel.loadNotificationApps()
        priorityViewModel.loadNotificationAppPriority()
        coroutineScope.launch {
            delay(500)
            isRefreshing = false
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.loadNotificationApps()
        priorityViewModel.loadNotificationAppPriority()
    }

    Scaffold(
        topBar = {
            MainTopAppBar()
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NotificationAppListView(navController, viewModel, priorityViewModel)
        }
    }
    PermissionCheck()
}