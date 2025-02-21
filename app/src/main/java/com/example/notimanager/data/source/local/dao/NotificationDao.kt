package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notimanager.data.dto.NotificationAppDto
import com.example.notimanager.data.dto.NotificationDto
import com.example.notimanager.data.dto.NotificationTitleDto
import com.example.notimanager.data.model.NotificationModel

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationModel): Long

    @Query("""
        SELECT n1.appName, n1.title, n1.content, n1.timestamp
        FROM notification AS n1
        WHERE timestamp = (
            SELECT MAX(timestamp)
            FROM notification AS n2
            WHERE n1.appName = n2.appName
        )
        ORDER BY timestamp DESC
    """)
    suspend fun getNotificationAppList(): List<NotificationAppDto>

    @Query("""
        SELECT n1.title, n1.content, n1.timestamp
        FROM notification AS n1
        WHERE n1.appName = :appName AND n1.title = :title AND timestamp = (
            SELECT MAX(timestamp)
            FROM notification AS n2
            WHERE n1.appName = n2.appName AND n1.title = n2.title
        )
        ORDER BY timestamp DESC
    """)
    suspend fun getNotificationTitleList(appName: String, title: String): List<NotificationTitleDto>

    @Query("""
        SELECT n.title, n.content, n.timestamp, nm.intentActive, nm.intentArray
        FROM notification AS n
        INNER JOIN notification_meta AS nm ON n.id = nm.notificationId
        WHERE n.appName = :appName AND n.title = :title
        ORDER BY timestamp DESC
    """)
    suspend fun getNotificationList(appName: String, title: String): List<NotificationDto>
}