package com.example.notimanager.presentation.ui.navigation

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun HandleBackPress(navController: NavHostController, startRoute: String) {
    // popBackStack 호출 여부를 추적하는 상태 변수
    val hasPoppedBackStack = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    BackHandler(enabled = true) {
        if (!hasPoppedBackStack.value && navController.currentDestination?.route != startRoute) {
            hasPoppedBackStack.value = true
            navController.popBackStack()
            hasPoppedBackStack.value = false
        } else {
            // TODO: 뒤로가기 시 광고를 띄운 종료창을 띄우도록 하기.
            (context as? Activity)?.finish()
        }
    }
}
