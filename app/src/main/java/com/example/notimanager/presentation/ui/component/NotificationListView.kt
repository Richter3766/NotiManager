package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.Notification
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.presentation.stateholder.state.NotificationState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationViewModel

@Composable
fun NotificationListView(viewModel: NotificationViewModel) {
    val notificationState by viewModel.notificationState.observeAsState(NotificationState())

    Column {
        if (notificationState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (notificationState.error != null) {

        } else {
            LazyColumn {
                items(notificationState.notificationList) { notification ->
                    NotificationItemView (notification = notification, onClick = {

                    })
                }
            }
        }
    }
}

@Composable
fun NotificationItemView(notification: Notification, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
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