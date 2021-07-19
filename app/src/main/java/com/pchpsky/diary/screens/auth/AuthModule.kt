package com.pchpsky.diary.screens.auth

import com.pchpsky.diary.datasourse.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {

    @Singleton
    @Provides
    fun provideAuthRepository(networkClient: NetworkClient): AuthRepository {
        return AppAuthRepository(networkClient)
    }
}