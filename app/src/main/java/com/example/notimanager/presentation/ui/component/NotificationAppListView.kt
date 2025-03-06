package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.R
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.presentation.stateholder.state.NotificationAppPriorityState
import com.example.notimanager.presentation.stateholder.state.NotificationAppState
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppPriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel

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
    }

    LaunchedEffect(notificationAppState.notificationAppList) {
        if (!notificationAppState.isLoading) {
            currentNoti = notificationAppState.notificationAppList
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

        item {
            HorizontalDivider()
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

@Composable
fun NotificationAppItemView(
    notification: NotificationApp,
    onClick: () -> Unit,
    viewModel: NotificationAppViewModel,
    priorityViewModel: NotificationAppPriorityViewModel,
    filteredNotificationViewModel: FilteredNotificationViewModel = hiltViewModel()
) {
    // 언어 변경에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val addFiltered = context.getString(R.string.modal_add_filtered)
    val addPriority = context.getString(R.string.modal_add_priority)
    val removeFiltered = context.getString(R.string.modal_remove_filtered)
    val removePriority = context.getString(R.string.modal_remove_priority)
    val delete = context.getString(R.string.modal_delete)
    // 위의 문자열 리소스는 모달에서 사용할 텍스트
    
    var showModal by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.appIcon)
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = notification.appName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
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
                text = formatTimestamp(context, notification.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        }
        
        // 더보기
        IconButton(onClick = { showModal = true }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "중요 표시 또는 삭제")
        }
    }
    // 더보기 클릭 시 나오는 모달창
    if (showModal) {
        BottomSheet(showModal, onDismiss = { showModal = false }){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 알림 제목
                Text(
                    text = notification.appName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
                
                // 상단 고정 여부 버튼
                if (notification.priorityActive) {
                    ClickableTextView(text = removePriority, onClick = {
                        priorityViewModel.removeAppPriority(notification.appName){
                            viewModel.loadNotificationApps()
                        }
                        showModal = false
                    })
                }
                else{
                    ClickableTextView(text = addPriority, onClick = {
                        viewModel.setAppPriority(notification.appName, priorityViewModel.getLength()){
                            priorityViewModel.loadNotificationAppPriority()
                        }
                        showModal = false
                    })
                }
                
                // 삭제 버튼
                ClickableTextView(text = delete, onClick = {
                    viewModel.deleteNotificationApp(notification.appName)
                    showModal = false
                })

                // 수집 여부 버튼
                ClickableTextView(text = if (notification.filteredId == 0L) addFiltered else removeFiltered, onClick = {
                    if (notification.filteredId == 0L) filteredNotificationViewModel.insertFilteredNoti(notification.appName, ""){
                        viewModel.loadNotificationApps()
                        priorityViewModel.loadNotificationAppPriority()
                    }
                    else filteredNotificationViewModel.deleteFilteredNoti(notification.filteredId){
                        viewModel.loadNotificationApps()
                        priorityViewModel.loadNotificationAppPriority()
                    }
                    showModal = false
                })
            }
        }
    }
}

@Composable
@Preview
fun PreviewNotificationAppItemView(){
    MaterialTheme{
        NotificationAppItemView(
            notification = NotificationApp(
                appName = "appName",
                title = "title",
                content = "content",
                timestamp = 1234567890,
                appIcon = null,
                priorityActive = false,
                priority = 0,
                filteredId = 0L
            ), onClick = {},
            viewModel = hiltViewModel(),
            priorityViewModel = hiltViewModel()
        )
    }
}