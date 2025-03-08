package com.example.notimanager.presentation.ui.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notimanager.R
import com.example.notimanager.domain.model.FilteredNotification
import com.example.notimanager.presentation.stateholder.viewmodel.FilteredNotificationViewModel
import com.example.notimanager.presentation.ui.component.common.BottomSheet
import com.example.notimanager.presentation.ui.component.common.ClickableTextView

@Composable
fun FilteredItemView(
    filteredItem: FilteredNotification,
    viewModel: FilteredNotificationViewModel,
) {
    // 언어 설정에 따라 문자열 리소스를 가져오기
    val context = LocalContext.current
    val removeFiltered = context.getString(R.string.modal_remove_filtered)

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

                ClickableTextView(text = removeFiltered, onClick = {
                    viewModel.deleteFilteredNoti(filteredItem.id){
                        viewModel.loadFilteredNoti()
                    }
                    showModal = false
                })
            }
        }
    }
}