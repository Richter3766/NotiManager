package com.example.notimanager.di

import com.example.notimanager.domain.usecase.NotificationPermissionUseCase
import com.example.notimanager.presentation.stateholder.viewmodel.NotificationPermissionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNotificationPermissionViewModel(
        notificationPermissionUseCase: NotificationPermissionUseCase
    ): NotificationPermissionViewModel {
        return NotificationPermissionViewModel(notificationPermissionUseCase)
    }
}