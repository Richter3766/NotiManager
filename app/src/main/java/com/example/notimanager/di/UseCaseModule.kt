package com.example.notimanager.di

import com.example.notimanager.data.repository.FilteredNotificationRepository
import com.example.notimanager.domain.repository.FilteredNotificationRepositoryInterface
import com.example.notimanager.domain.repository.NotificationAppRepositoryInterface
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import com.example.notimanager.domain.repository.NotificationDomainRepositoryInterface
import com.example.notimanager.domain.repository.NotificationTitleRepositoryInterface
import com.example.notimanager.domain.usecase.FilteredNotificationUseCase
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
        notificationRepository: NotificationDomainRepositoryInterface
    ): NotificationUseCase {
        return NotificationUseCase(notificationRepository)
    }

    @Provides
    @Singleton
    fun provideNotificationAppUseCase(
        notificationAppRepository: NotificationAppRepositoryInterface
    ): NotificationAppUseCase {
        return NotificationAppUseCase(notificationAppRepository)
    }

    @Provides
    @Singleton
    fun provideNotificationTitleUseCase(
        notificationTitleRepository: NotificationTitleRepositoryInterface
    ): NotificationTitleUseCase {
        return NotificationTitleUseCase(notificationTitleRepository)
    }

    @Provides
    @Singleton
    fun provideFilteredNotificationUseCase(
        filteredNotificationRepository: FilteredNotificationRepositoryInterface
    ): FilteredNotificationUseCase {
        return FilteredNotificationUseCase(filteredNotificationRepository)
    }
}