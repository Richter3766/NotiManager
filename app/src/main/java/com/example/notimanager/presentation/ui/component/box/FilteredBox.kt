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
fun AddFilteredBox(onClick: () -> Unit){
    val context = LocalContext.current
    val addFiltered = context.getString(R.string.modal_add_filtered)
    ClickableTextView(text = addFiltered, onClick = onClick){
        Image(
            painter = painterResource(id = R.drawable.notifications_off),
            contentDescription = "notifications off icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }
}

@Composable
fun RemoveFilteredBox(onClick: () -> Unit){
    val context = LocalContext.current
    val removeFiltered = context.getString(R.string.modal_remove_filtered)
    ClickableTextView(text = removeFiltered, onClick = onClick){
        Image(
            painter = painterResource(id = R.drawable.notifications),
            contentDescription = "notifications icon",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }
}