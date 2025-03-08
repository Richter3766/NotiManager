package com.example.notimanager.presentation.ui.component.box

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.notimanager.R

@Composable
fun DeleteBox(onClick: () -> Unit){
    val context = LocalContext.current
    val delete = context.getString(R.string.modal_delete)
    ClickableTextView(text = delete, onClick = onClick, )
}