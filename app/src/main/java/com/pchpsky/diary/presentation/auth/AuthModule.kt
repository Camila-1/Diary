package com.pchpsky.diary.presentation.auth

import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.network.NetworkClient
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