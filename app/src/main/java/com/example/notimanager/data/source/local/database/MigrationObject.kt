package com.example.notimanager.data.source.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationObject {
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE notification_icon RENAME COLUMN notificationIconResId iconBytes")
        }
    }

    val MIGRATION_6_7 = object : Migration(6, 7) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE INDEX index_notification_icon_priorityActive_priority ON notification_icon (priorityActive, priority);")
            db.execSQL("CREATE INDEX index_app_icon_priorityActive_priority ON app_icon (priorityActive, priority);")
        }
    }

    val MIGRATION_7_8 = object : Migration(7, 8) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE notification ADD COLUMN subText TEXT NOT NULL DEFAULT '';")
            db.execSQL("ALTER TABLE notification_meta ADD COLUMN isRead INTEGER NOT NULL DEFAULT '0';")
        }
    }

    val MIGRATION_8_9 = object : Migration(8, 9) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE INDEX index_notification_appName_subText_timestamp ON notification(appName, subText, timestamp);\n")
        }
    }
}