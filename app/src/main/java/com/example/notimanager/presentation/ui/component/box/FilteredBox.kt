package com.example.notimanager.presentation.ui.component.box

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.notimanager.R

@Composable
fun AddFilteredBox(onClick: () -> Unit){
    val context = LocalContext.current
    val addFiltered = context.getString(R.string.modal_add_filtered)
    ClickableTextView(text = addFiltered, onClick = onClick, )
}

@Composable
fun RemoveFilteredBox(onClick: () -> Unit){
    val context = LocalContext.current
    val removeFiltered = context.getString(R.string.modal_remove_filtered)
    ClickableTextView(text = removeFiltered, onClick = onClick, )
}