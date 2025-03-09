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
import com.example.notimanager.presentation.ui.component.box.SettingBoxView

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
    val filteredListExplanation = context.getString(R.string.setting_filtered_list_explanation)

    val accessPermission = context.getString(R.string.setting_access_permission)
    val accessPermissionExplanation = context.getString(R.string.setting_access_permission_explanation)

    val sendPermission = context.getString(R.string.setting_send_permission)
    val sendPermissionExplanation = context.getString(R.string.setting_send_permission_explanation)

    val dateFormatter = context.getString(R.string.setting_date_format)
    val dateFormatterExplanation = context.getString(R.string.setting_date_format_explanation)

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        // 받지 않는 알림 목록
        SettingBoxView(filteredList, filteredListExplanation) {
            navController.navigate("FilteredListScreen")
        }

        // 시간 형식 변경
        SettingBoxView(dateFormatter, dateFormatterExplanation) {
            navController.navigate("DateFormatterScreen")
        }

        // 알림 접근 권한
        SettingBoxView(accessPermission, accessPermissionExplanation) {
            servicePermissionViewModel.requestServicePermission()
        }

        // 알림 발송 권한
        SettingBoxView(sendPermission, sendPermissionExplanation) {
            notificationPermissionViewModel.requestPermission(context as Activity)
        }
    }
}