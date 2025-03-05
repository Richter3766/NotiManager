package com.example.notimanager.presentation.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel

@Composable
fun PermissionCheck(viewModel: NotificationServicePermissionViewModel = hiltViewModel()){
    val isPermissionGranted by viewModel.isNotificationServiceEnabled.observeAsState()

    if (isPermissionGranted == false) {
        SimplePermissionDialog(
            title = "알림 권한 필요",
            message = "알림을 받기 위해 알림 권한이 필요합니다.",
            onAllow = { viewModel.requestServicePermission() },
        )
    }
}

@Composable
fun SimplePermissionDialog(
    title: String,
    message: String,
    onAllow: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
            },
            text = {
                Text(text = message, style = MaterialTheme.typography.bodySmall)
            },
            confirmButton = {
                Button(onClick = {
                    onAllow()

                }) {
                    Text("허용")
                }
            }
        )
    }
}
