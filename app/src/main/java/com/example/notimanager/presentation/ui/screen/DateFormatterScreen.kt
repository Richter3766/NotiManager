package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.DateFormatterView
import com.example.notimanager.presentation.ui.component.SettingTopAppBar

@Composable
fun DateFormatterScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            SettingTopAppBar{ navController.popBackStack() }
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        DateFormatterView(innerPadding)
    }
}