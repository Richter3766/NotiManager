package com.example.notimanager.presentation.ui.component.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.notimanager.presentation.stateholder.state.NotificationState
import com.example.notimanager.presentation.ui.component.item.NotificationItemView


@Composable
fun NotificationListView(
    notificationState: NotificationState,
    onDelete: (Long) -> Unit

) {
    val context = LocalContext.current
    var currentNoti by remember { mutableStateOf(notificationState.notificationList) }

    LaunchedEffect(notificationState.notificationList) {
        if (!notificationState.isLoading) {
            currentNoti = notificationState.notificationList
        }
        if (notificationState.notificationList.isEmpty()){
            currentNoti = emptyList()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(currentNoti) { notification ->
            NotificationItemView (notification = notification, onClick = {
                if (notification.intent?.action != null)
                    context.startActivity(notification.intent) },
                onDelete = onDelete
            )
        }
    }
}