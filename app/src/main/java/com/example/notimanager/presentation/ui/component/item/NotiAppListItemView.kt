package com.example.notimanager.presentation.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.R
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppPriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel
import com.example.notimanager.presentation.ui.component.box.AddFilteredBox
import com.example.notimanager.presentation.ui.component.box.AddPriorityBox
import com.example.notimanager.presentation.ui.component.box.DeleteBox
import com.example.notimanager.presentation.ui.component.box.RemoveFilteredBox
import com.example.notimanager.presentation.ui.component.box.RemovePriorityBox
import com.example.notimanager.presentation.ui.component.common.AppIconView
import com.example.notimanager.presentation.ui.component.common.BottomSheet

@Composable
fun NotificationAppItemView(
    notification: NotificationApp,
    onClick: () -> Unit,
    viewModel: NotificationAppViewModel,
    priorityViewModel: NotificationAppPriorityViewModel,
    filteredNotificationViewModel: FilteredNotificationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = notification.appName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(2.dp))
                if(notification.priorityActive) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "중요 표시",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0XFF673AB7)
                    )
                }
                if(notification.filteredId != 0L) {
                    Image(
                        painter = painterResource(id = R.drawable.notifications_off),
                        contentDescription = "notification off icon",
                        modifier = Modifier.size(12.dp),
                        colorFilter = ColorFilter.tint(Color(0XFF673AB7))
                    )
                }
            }

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

        // 최신 알림 존재 여부
        if (!notification.isRead){
            Badge {
                Text("N")
            }
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
                
                // 삭제 버튼
                DeleteBox {
                    viewModel.deleteNotificationApp(notification.appName){
                        priorityViewModel.loadNotificationAppPriority()
                    }
                    showModal = false
                }

                // 관리 여부 버튼
                if (notification.filteredId == 0L){
                    AddFilteredBox {
                        filteredNotificationViewModel.insertFilteredNoti(notification.appName, ""){
                            viewModel.loadNotificationApps()
                            priorityViewModel.loadNotificationAppPriority()
                        }
                        showModal = false
                    }
                }
                else {
                    RemoveFilteredBox {
                        filteredNotificationViewModel.deleteFilteredNoti(notification.filteredId) {
                            viewModel.loadNotificationApps()
                            priorityViewModel.loadNotificationAppPriority()
                        }
                        showModal = false
                    }
                }
                // 중요 알림 설정
                if (notification.priorityActive) {
                    RemovePriorityBox {
                        priorityViewModel.removeAppPriority(notification.appName){
                            viewModel.loadNotificationApps()
                        }
                        showModal = false
                    }
                }
                else{
                    AddPriorityBox {
                        viewModel.setAppPriority(notification.appName, priorityViewModel.getLength()){
                            priorityViewModel.loadNotificationAppPriority()
                        }
                        showModal = false
                    }
                }
            }
        }
    }
}