package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        FilteredListView(innerPadding, viewModel)
    }
}