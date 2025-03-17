package com.notimanager.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.notimanager.notimanager.data.model.NotificationIconModel

@Dao
interface NotificationIconDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificationIconModel: NotificationIconModel): Long

    @Query("UPDATE notification_icon SET priorityActive = 1, priority = :newPriority WHERE notificationId = :notificationId")
    suspend fun setPriority(notificationId: Long, newPriority: Int): Int

    @Query("UPDATE notification_icon SET priorityActive = 0, priority = 0 WHERE notificationId = :notificationId")
    suspend fun removePriority(notificationId: Long): Int
}