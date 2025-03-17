package com.notimanager.notimanager.di

import com.notimanager.notimanager.domain.usecase.NotificationPermissionUseCase
import com.notimanager.notimanager.presentation.stateholder.viewmodel.NotificationServicePermissionViewModel
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
    ): NotificationServicePermissionViewModel {
        return NotificationServicePermissionViewModel(notificationPermissionUseCase)
    }
}