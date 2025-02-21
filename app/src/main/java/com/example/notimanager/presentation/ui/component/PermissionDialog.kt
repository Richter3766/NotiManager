package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

object PermissionDialog {
    @Composable
    fun SimplePermissionDialog(
        title: String,
        message: String,
        onAllow: () -> Unit
    ) {
        // 다이얼로그의 닫힘 상태를 관리할 MutableState
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { /* 바깥을 클릭해도 닫히지 않도록 설정 */ },
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


}