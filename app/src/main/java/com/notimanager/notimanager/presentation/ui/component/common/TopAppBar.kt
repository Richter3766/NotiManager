package com.notimanager.notimanager.presentation.ui.component.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.notimanager.notimanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(settingOnClick: () -> Unit){
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val appName = context.getString(R.string.app_name)

    TopAppBar(
        title = {
            Text(text = appName)
        },
        navigationIcon = {
            IconButton(onClick = settingOnClick) {
                Icon(Icons.Filled.Menu, contentDescription = "설정")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(title: String, onBackClick: () -> Unit){
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로 가기")
            }
        }
    )
}