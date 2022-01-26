package com.pchpsky.diary.di

import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.repositories.LoginRepository
import com.pchpsky.diary.data.repositories.RecordInsulinRepository
import com.pchpsky.diary.data.repositories.SettingsRepository
import com.pchpsky.diary.data.repositories.SignupRepository
import com.pchpsky.diary.data.repositories.interfacies.InsulinDataSource
import com.pchpsky.diary.data.repositories.interfacies.LoginController
import com.pchpsky.diary.data.repositories.interfacies.SettingsDataSource
import com.pchpsky.diary.data.repositories.interfacies.SignupController
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
    fun provideLoginRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): LoginController {
        return LoginRepository(networkClient, dataStoreManager)
    }

    @Provides
    @ViewModelScoped
    fun provideSignupRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): SignupController {
        return SignupRepository(networkClient, dataStoreManager)
    }
}