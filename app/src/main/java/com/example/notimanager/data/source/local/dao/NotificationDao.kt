package com.example.notimanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
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

    @Query(
        """
        SELECT n1.appName, n1.title, n1.content, n1.timestamp, ai.iconBytes, ai.priorityActive, ai.priority, fn.id AS filteredId
        FROM notification AS n1
        INNER JOIN app_icon AS ai ON n1.appName = ai.notiAppName
        LEFT OUTER JOIN filtered_notification AS fn ON n1.appName = fn.appName AND fn.title = ""
        WHERE ai.priorityActive = :priorityActive
        AND timestamp = (
            SELECT MAX(timestamp)
            FROM notification AS n2
            WHERE n1.appName = n2.appName
        )
        ORDER BY timestamp DESC
    """
    )
    suspend fun getNotificationAppList(priorityActive: Boolean): List<NotificationAppDto>

    @Query(
        """
        SELECT n1.id, n1.title, n1.subText, n1.content, n1.timestamp, ni.iconBytes, ni.priorityActive, ni.priority, fn.id AS filteredId
        FROM notification AS n1
        INNER JOIN notification_icon AS ni ON n1.id = ni.notificationId
        LEFT OUTER JOIN filtered_notification AS fn ON n1.appName = fn.appName AND fn.title = n1.title
        WHERE ni.priorityActive = :priorityActive
        AND n1.appName = :appName 
        AND n1.subText = ""
        AND n1.timestamp = (
            SELECT MAX(timestamp)
            FROM notification AS n2
            WHERE n1.appName = n2.appName AND n1.title = n2.title
        )
        AND n1.title IN (
            SELECT title
            FROM notification
            WHERE appName = :appName
            GROUP BY title
        )
        
        UNION ALL
        
        SELECT n1.id, n1.title, n1.subText, n1.content, n1.timestamp, ni.iconBytes, ni.priorityActive, ni.priority, fn.id AS filteredId
        FROM notification AS n1
        INNER JOIN notification_icon AS ni ON n1.id = ni.notificationId
        LEFT OUTER JOIN filtered_notification AS fn ON n1.appName = fn.appName AND fn.title = n1.subText
        WHERE ni.priorityActive = :priorityActive
        AND n1.appName = :appName 
        AND n1.subText != ""
        AND n1.timestamp = (
            SELECT MAX(timestamp)
            FROM notification AS n2
            WHERE n1.appName = n2.appName AND n1.subText = n2.subText
        )
        AND n1.subText IN (
            SELECT subText
            FROM notification
            WHERE appName = :appName
            GROUP BY subText
        )
        
        ORDER BY n1.timestamp DESC
    """
    )
    suspend fun getNotificationTitleList(appName: String, priorityActive: Boolean): List<NotificationTitleDto>

    @Query(
        """
        SELECT n.id, n.title, n.subText, n.content, n.timestamp, nm.intentActive, nm.intentArray, ni.iconBytes
        FROM notification AS n
        INNER JOIN notification_meta AS nm ON n.id = nm.notificationId
        INNER JOIN notification_icon AS ni ON n.id = ni.notificationId
        WHERE n.appName = :appName AND n.title = :title
        ORDER BY timestamp DESC
    """
    )
    suspend fun getNotificationList(appName: String, title: String): List<NotificationDto>

    @Query(
        """
        SELECT n.id, n.title, n.subText, n.content, n.timestamp, nm.intentActive, nm.intentArray, ni.iconBytes
        FROM notification AS n
        INNER JOIN notification_meta AS nm ON n.id = nm.notificationId
        INNER JOIN notification_icon AS ni ON n.id = ni.notificationId
        WHERE n.appName = :appName AND n.subText = :subText
        ORDER BY timestamp DESC
    """
    )
    suspend fun getNotificationSubTextList(appName: String, subText: String): List<NotificationDto>

    @Query("DELETE FROM Notification WHERE appName = :appName")
    suspend fun deleteNotificationByAppName(appName: String): Int

    @Query("DELETE FROM Notification WHERE appName = :appName AND title = :title")
    suspend fun deleteNotificationByTitle(appName: String, title: String): Int

    @Query("DELETE FROM Notification WHERE appName = :appName AND subText = :subText")
    suspend fun deleteNotificationBySubText(appName: String, subText: String): Int

    @Query("DELETE FROM Notification WHERE id = :id")
    suspend fun deleteNotificationById(id: Long): Int
}