package com.example.notimanager.presentation.ui.component.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.viewinterop.AndroidView
import com.example.notimanager.presentation.stateholder.state.NotificationState
import com.example.notimanager.presentation.ui.ads.AdsUtil.getAdView
import com.example.notimanager.presentation.ui.component.item.NotificationItemView


@Composable
fun NotificationListView(
    notificationState: NotificationState,
    onDelete: (Long) -> Unit

) {
    val context = LocalContext.current
    var currentNoti by remember { mutableStateOf(notificationState.notificationList) }

    val adView = getAdView(context)

    LaunchedEffect(notificationState.notificationList) {
        if (!notificationState.isLoading) {
            currentNoti = notificationState.notificationList
        }
        if (notificationState.notificationList.isEmpty()){
            currentNoti = emptyList()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 56.dp)
        ) {
            items(currentNoti) { notification ->
                NotificationItemView (notification = notification, onClick = {
                    if (notification.intent?.action != null)
                        context.startActivity(notification.intent) },
                    onDelete = onDelete
                )
            }

        }

        AndroidView(
            factory = { adView },
            update = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}