package com.pchpsky.diary.screens.auth

import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
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
    fun provideAuthRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): AuthRepository {
        return AppAuthRepository(networkClient, dataStoreManager)
    }
}