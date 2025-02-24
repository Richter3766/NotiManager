package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationApp
import com.example.notimanager.presentation.stateholder.state.NotificationAppState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationAppViewModel

@Composable
fun NotificationAppListView(navController: NavController, viewModel: NotificationAppViewModel = hiltViewModel()) {
    val notificationAppState by viewModel.notificationAppState.observeAsState(NotificationAppState())

    Column {
        if (notificationAppState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (notificationAppState.error != null) {

        } else {
            LazyColumn {
                items(notificationAppState.notificationAppList) { notification ->
                    NotificationAppItemView(notification = notification, onClick = {
                        navController.navigate(
                            "titleScreen/${notification.appName}/${notification.title}"
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun NotificationAppItemView(notification: NotificationApp, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.appIcon)
        Column {
            BasicText(text = notification.appName, style = MaterialTheme.typography.headlineMedium)
            BasicText(text = notification.title, style = MaterialTheme.typography.bodyLarge)
            BasicText(text = notification.content, style = MaterialTheme.typography.bodyMedium)
            BasicText(text = formatTimestamp(notification.timestamp), style = MaterialTheme.typography.bodyMedium)
        }
    }
}