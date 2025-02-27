package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notimanager.presentation.ui.component.MainTopAppBar
import com.example.notimanager.presentation.ui.component.NotificationAppListView
import com.example.notimanager.presentation.ui.component.PermissionCheck

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {
            MainTopAppBar()
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        NotificationAppListView(navController, innerPadding)
    }
    PermissionCheck()

}