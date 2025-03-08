package com.example.notimanager.presentation.ui.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notimanager.R
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.presentation.ui.component.common.AppIconView
import com.example.notimanager.presentation.ui.component.common.BottomSheet
import com.example.notimanager.presentation.ui.component.common.ClickableTextView

@Composable
fun NotificationItemView(
    notification: Notification,
    onClick: () -> Unit,
    onDelete: (Long) -> Unit
) {
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val moveToApp = context.getString(R.string.modal_move_to_app)
    val delete = context.getString(R.string.modal_delete)
    // 위의 문자열 리소스는 모달에서 사용할 텍스트

    var showModal by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                showModal = true
            }),
        verticalAlignment = Alignment.Top
    ) {
        AppIconView(notification.notificationIcon)
        Column (
            modifier = Modifier.padding(start = 16.dp)
        ){
            Text(
                text = notification.title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = notification.content,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = formatTimestamp(context, notification.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        }

        // 모달창
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
                    Text(
                        text = notification.content,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                    // 삭제
                    ClickableTextView(text = delete, onClick = {
                        onDelete(notification.id)
                        showModal = false
                    })

                    // 앱으로 이동하기
                    ClickableTextView(text = moveToApp, onClick = {
                        onClick()
                        showModal = false
                    })
                }
            }
        }
    }
}