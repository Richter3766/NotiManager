package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notimanager.data.model.AppIconModel

@Dao
interface AppIconDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(appIconModel: AppIconModel): Long

    @Query("UPDATE app_icon SET priorityActive = 1, priority = :newPriority WHERE notiAppName = :appName")
    suspend fun setPriority(appName: String, newPriority: Int): Int

    @Query("UPDATE app_icon SET priorityActive = 0, priority = 0 WHERE notiAppName = :appName")
    suspend fun removePriority(appName: String): Int

}