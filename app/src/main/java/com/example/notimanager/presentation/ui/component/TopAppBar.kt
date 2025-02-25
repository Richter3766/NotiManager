package com.example.notimanager.presentation.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(){
    TopAppBar(
        title = {
            Text(text = "Notification Manager")
        },
        actions = {
            IconButton(onClick = { /* TODO: 클릭 이벤트 처리 */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "더 보기")
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTopAppBar(title: String, onBackClick: () -> Unit){
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
        },
        actions = {
            IconButton(onClick = { /* TODO: 클릭 이벤트 처리 */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "더 보기")
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationTopAppBar(title: String, onBackClick: () -> Unit){
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
        },
        actions = {
            IconButton(onClick = { /* TODO: 클릭 이벤트 처리 */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "더 보기")
            }
        })
}

@Preview(backgroundColor = 1)
@Composable
fun PreviewMainTopAppBar(){
    MaterialTheme{
        MainTopAppBar()
    }
}