package com.example.notimanager.di

import android.content.Context
import com.example.notimanager.data.repository.NotificationPermissionRepository
import com.example.notimanager.domain.repository.NotificationPermissionRepositoryInterface
import com.example.notimanager.domain.usecase.NotificationPermissionUseCase
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNotificationPermissionRepository(
        @ApplicationContext context: Context
    ): NotificationPermissionRepositoryInterface {
        return NotificationPermissionRepository(context)
    }

    @Provides
    @Singleton
    fun provideNotificationPermissionViewModel(
        notificationPermissionUseCase: NotificationPermissionUseCase
    ): NotificationPermissionViewModel {
        return NotificationPermissionViewModel(notificationPermissionUseCase)
    }

    @Provides
    @Singleton
    fun provideNotificationPermissionUseCase(
        notificationPermissionRepository: NotificationPermissionRepositoryInterface
    ): NotificationPermissionUseCase {
        return NotificationPermissionUseCase(notificationPermissionRepository)
    }
}