package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.presentation.stateholder.state.NotificationTitlePriorityState
import com.example.notimanager.presentation.stateholder.state.NotificationTitleState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel

@Composable
fun NotificationTitleListView(
    navController: NavController,
    viewModel: NotificationTitleViewModel,
    priorityViewModel: NotificationTitlePriorityViewModel
) {
    val notificationTitleState by viewModel.notificationTitleState.observeAsState(
        NotificationTitleState()
    )
    val priorityState by priorityViewModel.notificationTitlePriorityState.observeAsState(
        NotificationTitlePriorityState()
    )
    var currentNotiPriority by remember { mutableStateOf(priorityState.notificationTitleList) }
    var currentNoti by remember { mutableStateOf(notificationTitleState.notificationTitleList) }

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

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(currentNotiPriority) { notification ->
            NotificationTitleItemView(notification = notification, onClick = {
                navController.navigate(
                    "notificationScreen/${viewModel.getAppName()}/${notification.title}"
                )
            }, viewModel = viewModel, priorityViewModel = priorityViewModel)
        }

        item {
            HorizontalDivider()
        }

        items(currentNoti) { notification ->
            NotificationTitleItemView(notification = notification, onClick = {
                navController.navigate(
                    "notificationScreen/${viewModel.getAppName()}/${notification.title}"
                )
            }, viewModel = viewModel, priorityViewModel = priorityViewModel)
        }
    }
}

@Composable
fun NotificationTitleItemView(
    notification: NotificationTitle,
    onClick: () -> Unit,
    viewModel: NotificationTitleViewModel,
    priorityViewModel: NotificationTitlePriorityViewModel
) {
    var showModal by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.notificationIcon)
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = notification.title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)

            )
            Text(
                text = notification.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = formatTimestamp(notification.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        }
        IconButton(onClick = { showModal = true }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "중요 표시 또는 삭제")
        }
    }

    if (showModal) {
        BottomSheet(showModal, onDismiss = { showModal = false }){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
                if (notification.priorityActive) {
                    ClickableTextView(text = "중요 알림 취소", onClick = {
                        priorityViewModel.removeTitlePriority(notificationId = notification.id){
                            viewModel.loadNotificationTitles()
                        }

                    })
                }
                else{
                    ClickableTextView(text = "중요 알림 설정", onClick = {
                        viewModel.setTitlePriority(notification.id, priorityViewModel.getLength()){
                            priorityViewModel.loadNotificationTitles()
                        }
                    })
                }
                ClickableTextView(text = "삭제", onClick = {})
            }
        }
    }
}