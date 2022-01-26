package com.pchpsky.diary.di

import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.repositories.RecordInsulinRepository
import com.pchpsky.diary.data.repositories.SettingsRepository
import com.pchpsky.diary.data.repositories.interfacies.InsulinDataSource
import com.pchpsky.diary.data.repositories.interfacies.SettingsDataSource
import com.pchpsky.diary.presentation.auth.AuthRepository
import com.pchpsky.diary.presentation.auth.interfaces.AuthController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoriesModule {

    @Provides
    @ViewModelScoped
    fun provideSettingsRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): SettingsDataSource {
        return SettingsRepository(
            networkClient,
            dataStoreManager
        )
    }

    @Provides
    @ViewModelScoped
    fun provideInsulinTakingRepository(networkClient: NetworkClient): InsulinDataSource {
        return RecordInsulinRepository(networkClient)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): AuthController {
        return AuthRepository(networkClient, dataStoreManager)
    }
}