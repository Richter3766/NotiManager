package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SettingView(
    innerPadding: PaddingValues,
    navController: NavController
){
    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        ClickableTextView("알림 제외 리스트") {
            navController.navigate("FilteredListScreen")
        }
    }
}