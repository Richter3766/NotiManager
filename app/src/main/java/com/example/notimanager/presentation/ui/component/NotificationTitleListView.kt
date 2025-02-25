package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notimanager.common.objects.DateFormatter.formatTimestamp
import com.example.notimanager.domain.model.NotificationTitle
import com.example.notimanager.presentation.stateholder.state.NotificationTitleState
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationTitleViewModel

@Composable
fun NotificationTitleListView(navController: NavController, innerPadding: PaddingValues,viewModel: NotificationTitleViewModel) {
    val notificationTitleState by viewModel.notificationTitleState.observeAsState(NotificationTitleState())

    Column (
        Modifier.padding(innerPadding)
    ){
        if (notificationTitleState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (notificationTitleState.error != null) {

        } else {
            LazyColumn {
                items(notificationTitleState.notificationTitleList) { notification ->
                    NotificationTitleItemView (notification = notification, onClick = {
                        navController.navigate(
                            "notificationScreen/${viewModel.getAppName()}/${notification.title}"
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun NotificationTitleItemView(notification: NotificationTitle, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconView(notification.notificationIcon)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
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
    }
}