package com.example.notimanager.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notimanager.R
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.ui.component.common.CommonTopAppBar
import com.example.notimanager.presentation.ui.component.list.FilteredListView

@Composable
fun FilteredListScreen(
    navController: NavController,
    viewModel: FilteredNotificationViewModel = hiltViewModel()
) {
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val title = context.getString(R.string.setting_filtered_list)
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopAppBar(title = title, onBackClick = {
                if (!isClicked){
                    isClicked = true
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }

            })
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        FilteredListView(innerPadding, viewModel)
    }
}