package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notimanager.data.model.NotificationMetaModel

@Dao
interface NotificationMetaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificationMetaModel: NotificationMetaModel): Long

    @Query("SELECT * FROM notification_meta")
    suspend fun getAll(): List<NotificationMetaModel>
}