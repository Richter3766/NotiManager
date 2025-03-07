package com.example.notimanager.presentation.ui.component

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.R
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel

@Composable
fun SettingView(
    innerPadding: PaddingValues,
    navController: NavController,
    servicePermissionViewModel: NotificationServicePermissionViewModel = hiltViewModel(),
    notificationPermissionViewModel: NotificationPermissionViewModel = hiltViewModel(),

    ){
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val filteredList = context.getString(R.string.setting_filtered_list)
    val accessPermission = context.getString(R.string.setting_access_permission)
    val sendPermission = context.getString(R.string.setting_send_permission)

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        ClickableTextView(filteredList) {
            navController.navigate("FilteredListScreen")
        }

        ClickableTextView(accessPermission) {
            servicePermissionViewModel.requestServicePermission()
        }

        ClickableTextView(sendPermission) {
            notificationPermissionViewModel.requestPermission(context as Activity)
        }
    }
}