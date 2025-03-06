package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.notimanager.R

@Composable
fun SettingView(
    innerPadding: PaddingValues,
    navController: NavController
){
    val context = LocalContext.current
    val name = context.getString(R.string.setting_filtered_list)
    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        ClickableTextView(name) {
            navController.navigate("FilteredListScreen")
        }
    }
}