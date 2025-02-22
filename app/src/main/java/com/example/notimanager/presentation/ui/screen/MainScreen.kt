package com.example.notimanager.presentation.ui.screen

import androidx.compose.runtime.Composable
import com.example.notimanager.presentation.ui.component.NotificationListView
import com.example.notimanager.presentation.ui.component.PermissionCheck

@Composable
fun MainScreen(){
    PermissionCheck()
    NotificationListView()

}