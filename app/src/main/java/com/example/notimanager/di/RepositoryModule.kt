package com.example.notimanager.di

import android.content.Context
import com.example.notimanager.data.repository.NotificationPermissionRepository
import com.example.notimanager.data.repository.NotificationRepository
import com.example.notimanager.data.source.local.dao.NotificationDao
import com.example.notimanager.data.source.local.dao.NotificationMetaDao
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
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
    fun provideNotificationRepository(
        notificationDao: NotificationDao,
        notificationMetaDao: NotificationMetaDao
    ): NotificationRepositoryInterface {
        return NotificationRepository(notificationDao, notificationMetaDao)
    }
}