package com.example.notimanager.di

import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import com.example.notimanager.domain.repository.NotificationRepositoryInterface
import com.example.notimanager.domain.usecase.NotificationAppUseCase
import com.example.notimanager.domain.usecase.NotificationPermissionUseCase
import com.example.notimanager.domain.usecase.NotificationTitleUseCase
import com.example.notimanager.domain.usecase.NotificationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideNotificationPermissionUseCase(
        notificationPermissionRepository: NotificationPermissionRepositoryInterface
    ): NotificationPermissionUseCase {
        return NotificationPermissionUseCase(notificationPermissionRepository)
    }

    @Provides
    @Singleton
    fun provideNotificationUseCase(
        notificationRepository: NotificationRepositoryInterface
    ): NotificationUseCase {
        return NotificationUseCase(notificationRepository)
    }

    @Provides
    @Singleton
    fun provideNotificationAppUseCase(
        notificationRepository: NotificationRepositoryInterface
    ): NotificationAppUseCase {
        return NotificationAppUseCase(notificationRepository)
    }

    @Provides
    @Singleton
    fun provideNotificationTitleUseCase(
        notificationRepository: NotificationRepositoryInterface
    ): NotificationTitleUseCase {
        return NotificationTitleUseCase(notificationRepository)
    }
}