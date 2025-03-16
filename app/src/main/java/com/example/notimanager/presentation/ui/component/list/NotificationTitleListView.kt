package com.example.notimanager.presentation.ui.component.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.notimanager.common.objects.Encoder.getEncodedString
import com.example.notimanager.presentation.stateholder.state.NotificationTitlePriorityState
import com.example.notimanager.presentation.stateholder.state.NotificationTitleState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.ads.AdsUtil.getAdView
import com.example.notimanager.presentation.ui.component.item.NotificationTitleItemView

@Composable
fun NotificationTitleListView(
    navController: NavController,
    viewModel: NotificationTitleViewModel,
    priorityViewModel: NotificationTitlePriorityViewModel,
) {
    val notificationTitleState by viewModel.notificationTitleState.observeAsState(
        NotificationTitleState()
    )
    val priorityState by priorityViewModel.notificationTitlePriorityState.observeAsState(
        NotificationTitlePriorityState()
    )
    var currentNotiPriority by remember { mutableStateOf(priorityState.notificationTitleList) }
    var currentNoti by remember { mutableStateOf(notificationTitleState.notificationTitleList) }

    val context = LocalContext.current
    val adView = getAdView(context)

    LaunchedEffect(priorityState.notificationTitleList) {
        if (!priorityState.isLoading) {
            currentNotiPriority = priorityState.notificationTitleList
        }
    }

    LaunchedEffect(notificationTitleState.notificationTitleList) {
        if (!notificationTitleState.isLoading) {
            currentNoti = notificationTitleState.notificationTitleList
        }
    }

    Box(){
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 56.dp)
        ) {
            if(currentNotiPriority.isNotEmpty()){
                items(currentNotiPriority) { notification ->
                    NotificationTitleItemView(notification = notification, onClick = {
                        if (notification.subText == "") navController.navigate("notificationScreen/${viewModel.getAppName()}/${getEncodedString(notification.title)}/False")
                        else navController.navigate("notificationScreen/${viewModel.getAppName()}/${getEncodedString(notification.subText)}/True")
                    }, viewModel = viewModel, priorityViewModel = priorityViewModel)
                }

                item {
                    HorizontalDivider()
                }
            }
            if(currentNoti.isNotEmpty()){
                items(currentNoti) { notification ->
                    NotificationTitleItemView(notification = notification, onClick = {
                        if (notification.subText == "") {
                            viewModel.updateAsRead(notification.title)
                            navController.navigate(
                                "notificationScreen/${viewModel.getAppName()}/${
                                    getEncodedString(
                                        notification.title
                                    )
                                }/False"
                            )
                        }
                        else {
                            viewModel.updateAsSubText(notification.subText)
                            navController.navigate(
                                "notificationScreen/${viewModel.getAppName()}/${
                                    getEncodedString(
                                        notification.subText
                                    )
                                }/True"
                            )
                        }}, viewModel = viewModel, priorityViewModel = priorityViewModel)
                }
            }

        }

        AndroidView(
            factory = { adView },
            update = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
        )
    }

}