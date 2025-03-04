package com.example.notimanager.data.source.local.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notimanager.data.model.AppIconModel
import com.example.notimanager.data.model.FilteredNotificationModel
import com.example.notimanager.data.model.NotificationIconModel
import com.example.notimanager.data.model.NotificationMetaModel
import com.example.notimanager.data.model.NotificationModel
import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.FilteredNotificationDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.data.source.local.database.MigrationObject.MIGRATION_10_11
import com.example.notimanager.data.source.local.database.MigrationObject.MIGRATION_3_4
import com.example.notimanager.data.source.local.database.MigrationObject.MIGRATION_6_7
import com.example.notimanager.data.source.local.database.MigrationObject.MIGRATION_7_8
import com.example.notimanager.data.source.local.database.MigrationObject.MIGRATION_8_9

@Database(
    version = 11,
    entities =
    [
        NotificationModel::class,
        NotificationMetaModel::class,
        NotificationIconModel::class,
        AppIconModel::class,
        FilteredNotificationModel::class
    ],
    autoMigrations = [
        AutoMigration (from = 9, to = 10)
    ]
)
abstract class NotiManagerDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    abstract fun notificationIntentDao(): NotificationMetaDao
    abstract fun notificationIconDao(): NotificationIconDao
    abstract fun appIconDao(): AppIconDao
    abstract fun filteredNotificationDao(): FilteredNotificationDao

    companion object {
        @Volatile
        private var INSTANCE: NotiManagerDatabase? = null

        fun getDatabase(context: Context): NotiManagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotiManagerDatabase::class.java,
                    "notimanager_database"
                )
                    .addMigrations(
                        MIGRATION_3_4,
                        MIGRATION_6_7,
                        MIGRATION_7_8,
                        MIGRATION_8_9,
                        MIGRATION_10_11,
                        )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}