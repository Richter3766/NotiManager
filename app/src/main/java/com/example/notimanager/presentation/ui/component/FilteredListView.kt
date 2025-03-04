package com.example.notimanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notimanager.domain.model.FilteredNotification
import com.example.notimanager.presentation.stateholder.state.FilteredNotificationState
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel

@Composable
fun FilteredListView(
    innerPadding: PaddingValues,
    viewModel: FilteredNotificationViewModel
) {
    val filteredNotificationState by viewModel.filteredNotiState.observeAsState(FilteredNotificationState())
    if (filteredNotificationState.filteredList.isEmpty()){
        Text("No notifications")
    }
    else{
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            items(filteredNotificationState.filteredList) { item ->
                FilteredItemView(item, viewModel)
            }
        }
    }

}

@Composable
fun FilteredItemView(
    filteredItem: FilteredNotification,
    viewModel: FilteredNotificationViewModel,
) {
    var showModal by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = {showModal = true})
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        AppIconView(filteredItem.appIcon)
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = filteredItem.appName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text(
                text = filteredItem.title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
    if (showModal) {
        BottomSheet(showModal, onDismiss = { showModal = false }){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = filteredItem.appName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )

                ClickableTextView(text = "제외 리스트에서 삭제", onClick = {
                    viewModel.deleteFilteredNoti(filteredItem.id){
                        viewModel.loadFilteredNoti()
                    }
                    showModal = false
                })
            }
        }
    }
}