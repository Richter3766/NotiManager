package com.example.notimanager.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notimanager.data.model.NotificationIntentModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIntentDao

@Database(entities = [NotificationModel::class, NotificationIntentModel::class], version = 1)
abstract class NotiManagerDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    abstract fun notificationIntentDao(): NotificationIntentDao

    companion object {
        @Volatile
        private var INSTANCE: NotiManagerDatabase? = null

        fun getDatabase(context: Context): NotiManagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotiManagerDatabase::class.java,
                    "notimanager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}