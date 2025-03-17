package com.notimanager.notimanager.data.repository

import com.notimanager.notimanager.data.model.FilteredNotificationModel
import com.notimanager.notimanager.data.source.local.dao.FilteredNotificationDao
import com.notimanager.notimanager.domain.model.FilteredListItem
import com.notimanager.notimanager.domain.model.FilteredNotification
import com.notimanager.notimanager.domain.repository.FilteredNotificationRepositoryInterface


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
    ): List<FilteredListItem> {
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