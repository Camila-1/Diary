package com.pchpsky.diary.di

import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.presentation.auth.AuthRepository
import com.pchpsky.diary.presentation.auth.interfaces.AuthController
import com.pchpsky.diary.presentation.record.RecordRepository
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.diary.presentation.settings.UserSettingsRepository
import com.pchpsky.diary.presentation.settings.interfaces.SettingsRepository
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
        return UserSettingsRepository(networkClient, dataStoreManager)
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