package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notimanager.data.model.NotificationModel

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationModel): Long

    @Query("SELECT * FROM notification")
    suspend fun getAll(): List<NotificationModel>
}