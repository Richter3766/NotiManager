package com.example.notimanager.domain.usecase

import com.example.notimanager.domain.model.FilteredNotification
import com.example.notimanager.domain.repository.FilteredNotificationRepositoryInterface

class FilteredNotificationUseCase(
    private val repository: FilteredNotificationRepositoryInterface
){
    suspend fun insertFiltered(appName: String, title: String): Long{
        return repository.insertFiltered(appName, title)
    }

    suspend fun getFilteredList(): List<FilteredNotification>{
        return repository.getFilteredList()
    }

    suspend fun deleteById(id: Long): Int{
        return repository.deleteById(id)
    }
}