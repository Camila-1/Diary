package com.pchpsky.diary.screens.auth

import com.pchpsky.diary.datasource.localstorage.DataStoreManager
import com.pchpsky.diary.datasource.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class AuthModule {

    @Provides
    fun provideAuthRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): AuthRepository {
        return AuthRepository(networkClient, dataStoreManager)
    }
}