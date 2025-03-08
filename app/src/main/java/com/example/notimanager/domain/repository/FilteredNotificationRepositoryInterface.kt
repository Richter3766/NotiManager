package com.example.notimanager.domain.repository

import com.example.notimanager.domain.model.FilteredListItem
import com.example.notimanager.domain.model.FilteredNotification

interface FilteredNotificationRepositoryInterface {
    suspend fun insertFiltered(appName: String, title: String): Long
    suspend fun getSpecificFilteredList(appName: String, title: String, subText: String): List<FilteredListItem>
    suspend fun getFilteredList(): List<FilteredNotification>
    suspend fun deleteById(id: Long): Int
}