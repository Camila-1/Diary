package com.pchpsky.diary.di

import com.pchpsky.diary.datasource.localstorage.DataStoreManager
import com.pchpsky.diary.datasource.network.NetworkClient
import com.pchpsky.diary.screens.auth.AuthRepository
import com.pchpsky.diary.screens.auth.interfaces.AuthController
import com.pchpsky.diary.screens.record.RecordRepository
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.diary.screens.settings.SettingsRepository
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
    fun provideSettingsRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): SettingsRepository {
        return SettingsRepository(networkClient, dataStoreManager)
    }

    @Provides
    @ViewModelScoped
    fun provideInsulinTakingRepository(networkClient: NetworkClient): RecordInsulinRepository {
        return RecordRepository(networkClient)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): AuthController {
        return AuthRepository(networkClient, dataStoreManager)
    }
}