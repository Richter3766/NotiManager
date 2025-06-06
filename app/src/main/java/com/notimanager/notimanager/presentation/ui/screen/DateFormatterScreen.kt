package com.notimanager.notimanager.presentation.ui.screen

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
import androidx.navigation.NavController
import com.notimanager.notimanager.R
import com.notimanager.notimanager.presentation.ui.component.common.CommonTopAppBar
import com.notimanager.notimanager.presentation.ui.component.DateFormatterView

@Composable
fun DateFormatterScreen(
    navController: NavController,
) {
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val title = context.getString(R.string.setting_date_format)
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopAppBar(title) {
                if (!isClicked) {
                    isClicked = true
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalDivider(
            modifier = Modifier.padding(innerPadding),
            thickness = 0.2.dp
        )
        DateFormatterView(innerPadding)
    }
}