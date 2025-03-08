package com.example.notimanager.presentation.ui.component.box

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.notimanager.R

@Composable
fun AddPriorityBox(onClick: () -> Unit){
    val context = LocalContext.current
    val addPriority = context.getString(R.string.modal_add_priority)
    ClickableTextView(text = addPriority, onClick = onClick, )
}

@Composable
fun RemovePriorityBox(onClick: () -> Unit){
    val context = LocalContext.current
    val removePriority = context.getString(R.string.modal_remove_priority)
    ClickableTextView(text = removePriority, onClick = onClick, )
}