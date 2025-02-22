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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.presentation.stateholder.state.NotificationAppState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel

@Composable
fun NotificationListView(viewModel: NotificationAppViewModel = hiltViewModel()) {
    val notificationAppState by viewModel.notificationAppState.observeAsState(NotificationAppState())

    Column {
        if (notificationAppState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (notificationAppState.error != null) {
            // 오류 메시지 표시

        } else {
            // 리스트 표시
            LazyColumn {
                items(notificationAppState.notificationAppList) { notification ->
                    NotificationItemView(notification = notification)
                }
            }
        }
    }
}

@Composable
fun NotificationItemView(notification: NotificationApp) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.appIconResId)
        Column {
            BasicText(text = notification.appName, style = MaterialTheme.typography.headlineMedium)
            BasicText(text = notification.title, style = MaterialTheme.typography.bodyLarge)
            BasicText(text = notification.content, style = MaterialTheme.typography.bodyMedium)
            BasicText(text = formatTimestamp(notification.timestamp), style = MaterialTheme.typography.bodyMedium)
        }
    }
}