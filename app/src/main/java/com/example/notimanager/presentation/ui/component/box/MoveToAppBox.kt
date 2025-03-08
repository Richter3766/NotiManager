package com.example.notimanager.presentation.ui.component.box

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.notimanager.R

@Composable
fun MoveToAppBox(onClick: () -> Unit){
    val context = LocalContext.current
    val moveToApp = context.getString(R.string.modal_move_to_app)
    ClickableTextView(text = moveToApp, onClick = onClick, )
}