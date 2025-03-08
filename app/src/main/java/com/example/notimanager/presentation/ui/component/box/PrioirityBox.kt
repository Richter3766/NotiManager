package com.example.notimanager.presentation.ui.component.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notimanager.R

@Composable
fun AddPriorityBox(onClick: () -> Unit){
    val context = LocalContext.current
    val addPriority = context.getString(R.string.modal_add_priority)
    ClickableTextView(text = addPriority, onClick = onClick){
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "filled star icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
    }
}

@Composable
fun RemovePriorityBox(onClick: () -> Unit){
    val context = LocalContext.current
    val removePriority = context.getString(R.string.modal_remove_priority)
    ClickableTextView(text = removePriority, onClick = onClick){
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "star icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }
}