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
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitlePriorityViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel
import com.example.notimanager.presentation.ui.component.box.AddFilteredBox
import com.example.notimanager.presentation.ui.component.box.AddPriorityBox
import com.example.notimanager.presentation.ui.component.common.AppIconView
import com.example.notimanager.presentation.ui.component.common.BottomSheet
import com.example.notimanager.presentation.ui.component.box.DeleteBox
import com.example.notimanager.presentation.ui.component.box.RemoveFilteredBox
import com.example.notimanager.presentation.ui.component.box.RemovePriorityBox

@Composable
fun NotificationTitleItemView(
    notification: NotificationTitle,
    onClick: () -> Unit,
    viewModel: NotificationTitleViewModel,
    priorityViewModel: NotificationTitlePriorityViewModel,
    filteredNotificationViewModel: FilteredNotificationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
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
            // 알림 제목, 옆에 중요 알림, 필터링 알림일 경우에 대한 아이콘 표시
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (notification.subText == "") notification.title else notification.subText ,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
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

            // 알림 내용
            Text(
                text = notification.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            // 알림 받은 시간
            Text(
                text = formatTimestamp(context, notification.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        }
        
        // 읽지 않은 알림 수 표시 뱃지
        if (notification.unreadCount != 0){
            Badge {
                Text(notification.unreadCount.toString())
            }
        }
        
        // 더보기 버튼, 클릭 시 모달창 켜짐
        IconButton(onClick = { showModal = true }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "더보기")
        }
    }

    // 모달창
    if (showModal) {
        BottomSheet(showModal, onDismiss = { showModal = false }){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 알림 제목
                Text(
                    text = if (notification.subText == "") notification.title else notification.subText,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )

                // 삭제 버튼
                DeleteBox {
                    if (notification.subText == "")
                        viewModel.deleteByTitle(notification.title) { priorityViewModel.loadNotificationTitles() }
                    else
                        viewModel.deleteBySubText(notification.subText) { priorityViewModel.loadNotificationTitles() }
                    showModal = false
                }

                // 특정 동작 완료 후 동작
                val onComplete: () -> Unit = {
                    viewModel.loadNotificationTitles()
                    priorityViewModel.loadNotificationTitles()
                }

                // 알림 관리하지 않기 버튼
                if(notification.filteredId == 0L) { // 관리 중 -> 관리 중 X
                    AddFilteredBox {
                        if (notification.subText == "") { // subText가 제목인 경우
                            filteredNotificationViewModel.insertFilteredNoti(
                                viewModel.getAppName(),
                                notification.title,
                                onComplete
                            )
                        }
                        else{ // title이 제목인 경우
                            filteredNotificationViewModel.insertFilteredNoti(
                                viewModel.getAppName(),
                                notification.subText,
                                onComplete
                            )
                        }
                        showModal = false
                    }
                } else{ // 관리 중 X -> 관리 중
                    RemoveFilteredBox {
                        if (notification.subText == "")
                            filteredNotificationViewModel.deleteFilteredNoti(notification.filteredId, onComplete)
                        else
                            filteredNotificationViewModel.deleteFilteredNoti(notification.filteredId, onComplete)
                        showModal = false
                    }
                }

                // 중요 알림 설정 버튼
                if (notification.priorityActive) { // 중요 알림일 때
                    RemovePriorityBox {
                        priorityViewModel.removeTitlePriority(notificationId = notification.id){
                        viewModel.loadNotificationTitles()
                    }
                        showModal = false
                    }
                }
                else{ // 중요 알림이 아닐 때
                    AddPriorityBox {
                        viewModel.setTitlePriority(notification.id, priorityViewModel.getLength()){
                            priorityViewModel.loadNotificationTitles()
                        }
                        showModal = false
                    }
                }
            }
        }
    }
}

