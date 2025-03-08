package com.example.notimanager.presentation.ui.component.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notimanager.R

@Composable
fun DeleteBox(onClick: () -> Unit){
    val context = LocalContext.current
    val delete = context.getString(R.string.modal_delete)
    ClickableTextView(text = delete, onClick = onClick) {
        Image(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = "delete icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }
}