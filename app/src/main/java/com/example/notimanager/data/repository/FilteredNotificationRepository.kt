package com.example.notimanager.data.repository

import com.example.notimanager.data.model.FilteredNotificationModel
import com.example.notimanager.data.source.local.dao.FilteredNotificationDao
import com.example.notimanager.domain.model.FilteredNotification
import com.example.notimanager.domain.repository.FilteredNotificationRepositoryInterface


class FilteredNotificationRepository(
    private val dao: FilteredNotificationDao
) : FilteredNotificationRepositoryInterface {
    override suspend fun insertFiltered(appName: String, title: String): Long {
        val filteredNotification = FilteredNotificationModel(
            appName = appName,
            title = title
        )
        return dao.insert(filteredNotification)
    }

    override suspend fun getSpecificFilteredList(
        appName: String,
        title: String,
        subText: String
    ): List<FilteredNotification> {
        return dao.getSpecificFilteredList(appName, title, subText)
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun getFilteredList(): List<FilteredNotification> {
        return dao.getFilteredList()
            .asSequence()
            .map { it.toDomain() }
            .toList()
    }

    override suspend fun deleteById(id: Long): Int {
        return dao.deleteById(id)
    }
}