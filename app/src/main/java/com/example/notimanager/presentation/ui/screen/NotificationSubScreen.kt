package com.example.notimanager.presentation.ui.screen

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
import com.example.notimanager.presentation.stateholder.state.NotificationState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationSubTextViewModel
import com.example.notimanager.presentation.ui.component.common.CommonTopAppBar
import com.example.notimanager.presentation.ui.component.list.NotificationListView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSubScreen(navController: NavController, appName: String = "", subText:String){
    val viewModel: NotificationSubTextViewModel = hiltViewModel()

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val onRefresh: () -> Unit = {
        isRefreshing = true
        viewModel.loadNotification()
        coroutineScope.launch {
            delay(500)
            isRefreshing = false
        }
    }

    LaunchedEffect(appName, subText) {
        viewModel.setArgs(appName, subText)
    }
    Scaffold(
        topBar = {
            CommonTopAppBar(title = subText, onBackClick = { navController.popBackStack() })
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