package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.presentation.stateholder.state.NotificationState


@Composable
fun NotificationListView(notificationState: NotificationState) {
    val context = LocalContext.current
    var currentNoti by remember { mutableStateOf(notificationState.notificationList) }

    LaunchedEffect(notificationState.notificationList) {
        if (!notificationState.isLoading) {
            currentNoti = notificationState.notificationList
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(currentNoti) { notification ->
            NotificationItemView (notification = notification, onClick = {
                if (notification.intent?.action != null) {
                    context.startActivity(notification.intent);
                }
            })
        }
    }
}

@Composable
fun NotificationItemView(notification: Notification, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.notificationIcon)
        Column {
            BasicText(text = notification.title, style = MaterialTheme.typography.bodyLarge)
            BasicText(text = notification.content, style = MaterialTheme.typography.bodyMedium)
            BasicText(text = formatTimestamp(notification.timestamp), style = MaterialTheme.typography.bodyMedium)
        }
    }
}