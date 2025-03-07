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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notimanager.R
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel

@Composable
fun PermissionCheck(viewModel: NotificationServicePermissionViewModel = hiltViewModel()){
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val title = context.getString(R.string.permission_title)
    val message = context.getString(R.string.permission_message)

    val isPermissionGranted by viewModel.isNotificationServiceEnabled.observeAsState()

    if (isPermissionGranted == false) {
        SimplePermissionDialog(
            title = title,
            message = message,
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
    val context = LocalContext.current
    val ok = context.getString(R.string.permission_ok)

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
                    Text(ok)
                }
            }
        )
    }
}
