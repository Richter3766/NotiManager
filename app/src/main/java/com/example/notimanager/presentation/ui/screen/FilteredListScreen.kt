package com.example.notimanager.presentation.ui.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.ui.component.FilteredListView
import com.example.notimanager.presentation.ui.component.FilteredTopAppBar


@Composable
fun FilteredListScreen(
    navController: NavController,
    viewModel: FilteredNotificationViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            FilteredTopAppBar{ navController.popBackStack() }
        }
    ) { innerPadding ->
        FilteredListView(innerPadding, viewModel)
    }
}