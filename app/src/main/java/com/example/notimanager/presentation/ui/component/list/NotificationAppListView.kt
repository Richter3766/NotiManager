package com.example.notimanager.presentation.ui.component.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.state.NotificationAppPriorityState
import com.example.notimanager.presentation.stateholder.state.NotificationAppState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppPriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel
import com.example.notimanager.presentation.ui.component.item.NotificationAppItemView

@Composable
fun NotificationAppListView(
    navController: NavController,
    viewModel: NotificationAppViewModel,
    priorityViewModel: NotificationAppPriorityViewModel
) {
    val notificationAppState by viewModel.notificationAppState.observeAsState(NotificationAppState())
    val priorityState by priorityViewModel.notificationAppPriorityState.observeAsState((NotificationAppPriorityState()))
    var currentNotiPriority by remember { mutableStateOf(priorityState.notificationAppList) }
    var currentNoti by remember { mutableStateOf(notificationAppState.notificationAppList) }

    LaunchedEffect(priorityState.notificationAppList) {
        if (!priorityState.isLoading) {
            currentNotiPriority = priorityState.notificationAppList
        }
        if (priorityState.notificationAppList.isEmpty()){
            currentNotiPriority = emptyList()
        }
    }

    LaunchedEffect(notificationAppState.notificationAppList) {
        if (!notificationAppState.isLoading) {
            currentNoti = notificationAppState.notificationAppList
        }
        if (notificationAppState.notificationAppList.isEmpty()){
            currentNoti = emptyList()
        }
    }

    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        items(currentNotiPriority) { notification ->
            NotificationAppItemView(
                notification = notification,
                onClick = {
                    navController
                        .navigate(
                            "titleScreen/${notification.appName}"
                        )
                },
                viewModel = viewModel,
                priorityViewModel = priorityViewModel
            )
        }
        if (currentNotiPriority.isNotEmpty()){
            item {
                HorizontalDivider()
            }
        }


        items(currentNoti) { notification ->
            NotificationAppItemView(
                notification = notification,
                onClick = {
                    navController
                        .navigate(
                            "titleScreen/${notification.appName}"
                        )
                },
                viewModel = viewModel,
                priorityViewModel = priorityViewModel
            )
        }
    }
}