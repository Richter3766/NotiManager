package com.notimanager.notimanager.presentation.ui.component.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.notimanager.notimanager.presentation.stateholder.state.FilteredNotificationState
import com.notimanager.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.notimanager.notimanager.presentation.ui.component.item.FilteredItemView

@Composable
fun FilteredListView(
    innerPadding: PaddingValues,
    viewModel: FilteredNotificationViewModel
) {
    val filteredNotificationState by viewModel.filteredNotiState.observeAsState(
        FilteredNotificationState()
    )
    if (filteredNotificationState.filteredList.isEmpty()) {
        Text("No notifications")
    } else {
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