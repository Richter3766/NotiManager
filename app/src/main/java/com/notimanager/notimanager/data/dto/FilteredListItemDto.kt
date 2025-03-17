package com.notimanager.notimanager.data.dto

import com.notimanager.notimanager.domain.model.FilteredListItem

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


