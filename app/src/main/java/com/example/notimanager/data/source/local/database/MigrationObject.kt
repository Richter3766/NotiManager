package com.example.notimanager.data.source.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationObject {
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE notification_icon RENAME COLUMN notificationIconResId iconBytes")
        }
    }

    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE app_icon RENAME COLUMN appIconResId iconBytes")
        }
    }
    val MIGRATION_6_7 = object : Migration(6, 7) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE INDEX index_notification_icon_priorityActive_priority ON notification_icon (priorityActive, priority);")
            db.execSQL("CREATE INDEX index_app_icon_priorityActive_priority ON app_icon (priorityActive, priority);")
        }
    }
}