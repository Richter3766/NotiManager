package com.example.notimanager.data.dto

import com.example.notimanager.domain.model.FilteredListItem

data class FilteredListItemDto(
    val id: Long,
    val appName: String,
    val title: String,
){
    fun toDomain(): FilteredListItem {
        return FilteredListItem(
            id = this.id,
            appName = this.appName,
            title = this.title
        )
    }
}


