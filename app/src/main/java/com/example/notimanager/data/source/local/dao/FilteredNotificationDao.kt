package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notimanager.data.dto.FilteredListItemDto
import com.example.notimanager.data.dto.FilteredNotificationDto
import com.example.notimanager.data.model.FilteredNotificationModel

@Dao
interface FilteredNotificationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(filteredNotificationModel: FilteredNotificationModel): Long

    @Query("""
        SELECT id, appName, title FROM filtered_notification
        WHERE appName = :appName AND (title = "" OR title = :title OR title = :subText)
    """)
    suspend fun getSpecificFilteredList(appName: String, title: String, subText: String): List<FilteredListItemDto>

    @Query("""
        SELECT id, appName, title, ai.iconBytes
        FROM filtered_notification AS fn
        INNER JOIN app_icon AS ai ON fn.appName = ai.notiAppName  
        ORDER BY appName ASC
    """)
    suspend fun getFilteredList(): List<FilteredNotificationDto>

    @Query("DELETE FROM filtered_notification WHERE id = :id")
    suspend fun deleteById(id: Long): Int

}