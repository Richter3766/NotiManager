package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.notimanager.data.model.NotificationIconModel

@Dao
interface NotificationIconDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificationIconModel: NotificationIconModel): Long
}