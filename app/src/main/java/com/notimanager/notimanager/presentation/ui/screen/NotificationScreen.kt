package com.notimanager.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.notimanager.notimanager.presentation.stateholder.state.NotificationState
import com.notimanager.notimanager.presentation.stateholder.viewmodel.NotificationViewModel
import com.notimanager.notimanager.presentation.ui.component.common.CommonTopAppBar
import com.notimanager.notimanager.presentation.ui.component.list.NotificationListView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController, appName: String = "", title:String = ""){
    val viewModel: NotificationViewModel = hiltViewModel()

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    var isClicked by remember { mutableStateOf(false) }

    val onRefresh: () -> Unit = {
        isRefreshing = true
        viewModel.loadNotification()
        coroutineScope.launch {
            delay(500)
            isRefreshing = false
        }
    }

    LaunchedEffect(appName, title) {
        viewModel.setArgs(appName, title)
    }
    Scaffold(
        topBar = {
            CommonTopAppBar(title = title, onBackClick = {
                if(!isClicked){
                    isClicked = true
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                } }
            )
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
            val notificationState by viewModel.notificationState.observeAsState(NotificationState())
            NotificationListView(notificationState, viewModel::deleteNotification)
        }

    }
}