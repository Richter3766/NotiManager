package com.notimanager.notimanager.di

import android.content.Context
import com.notimanager.notimanager.data.source.local.dao.AppIconDao
import com.notimanager.notimanager.data.source.local.dao.FilteredNotificationDao
import com.notimanager.notimanager.data.source.local.dao.NotificationDao
import com.notimanager.notimanager.data.source.local.dao.NotificationIconDao
import com.notimanager.notimanager.data.source.local.dao.NotificationMetaDao
import com.notimanager.notimanager.data.source.local.database.NotiManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideNotiManagerDatabase(@ApplicationContext context: Context): NotiManagerDatabase {
        return NotiManagerDatabase.getDatabase(context)
    }

    @Provides
    fun provideNotificationDao(database: NotiManagerDatabase): NotificationDao {
        return database.notificationDao()
    }

    @Provides
    fun provideNotificationMetaDao(database: NotiManagerDatabase): NotificationMetaDao {
        return database.notificationIntentDao()
    }

    @Provides
    fun provideNotificationIconDao(database: NotiManagerDatabase): NotificationIconDao {
        return database.notificationIconDao()
    }

    @Provides
    fun provideAppIconDao(database: NotiManagerDatabase): AppIconDao {
        return database.appIconDao()
    }

    @Provides
    fun provideFilteredNotificationDao(database: NotiManagerDatabase): FilteredNotificationDao {
        return database.filteredNotificationDao()
    }
}