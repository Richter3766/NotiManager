package com.example.notimanager.di

import android.content.Context
import com.example.notimanager.data.repository.FilteredNotificationRepository
import com.example.notimanager.data.repository.NotificationAppRepository
import com.example.notimanager.data.repository.NotificationPermissionRepository
import com.example.notimanager.data.repository.NotificationRepository
import com.example.notimanager.data.repository.NotificationDomainRepository
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
import com.example.notimanager.data.repository.NotificationTitleRepository
import com.example.notimanager.data.source.local.dao.AppIconDao
import com.example.notimanager.data.source.local.dao.FilteredNotificationDao
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationIconDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.domain.repository.FilteredNotificationRepositoryInterface
import com.example.notimanager.domain.repository.NotificationAppRepositoryInterface
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import com.example.notimanager.domain.repository.NotificationDomainRepositoryInterface
import com.example.notimanager.domain.repository.NotificationTitleRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideNotificationPermissionRepository(
        @ApplicationContext context: Context
    ): NotificationPermissionRepositoryInterface {
        return NotificationPermissionRepository(context)
    }

    @Provides
    @Singleton
    fun provideNotificationDomainRepository(
        notificationDao: NotificationDao
    ): NotificationDomainRepositoryInterface {
        return NotificationDomainRepository(notificationDao)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(
        notificationDao: NotificationDao,
        notificationMetaDao: NotificationMetaDao,
        notificationIconDao: NotificationIconDao,
        appIconDao: AppIconDao,
    ): NotificationRepositoryInterface {
        return NotificationRepository(notificationDao, notificationMetaDao, notificationIconDao, appIconDao)
    }

    @Provides
    @Singleton
    fun provideNotificationAppRepository(
        notificationDao: NotificationDao,
        appIconDao: AppIconDao
    ): NotificationAppRepositoryInterface {
        return NotificationAppRepository(notificationDao, appIconDao)
    }

    @Provides
    @Singleton
    fun provideNotificationTitleRepository(
        notificationDao: NotificationDao,
        notificationIconDao: NotificationIconDao
    ): NotificationTitleRepositoryInterface {
        return NotificationTitleRepository(notificationDao, notificationIconDao)
    }

    @Provides
    @Singleton
    fun provideFilteredNotificationRepository(
        filteredNotificationDao: FilteredNotificationDao
    ): FilteredNotificationRepositoryInterface {
        return FilteredNotificationRepository(filteredNotificationDao)
    }
}