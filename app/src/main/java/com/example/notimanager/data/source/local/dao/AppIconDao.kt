package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.notimanager.data.model.AppIconModel

@Dao
interface AppIconDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(appIconModel: AppIconModel): Long
}