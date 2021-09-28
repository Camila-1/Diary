package com.pchpsky.diary.screens.settings

import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
import com.pchpsky.diary.datasourse.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class SettingsModule {

    @Provides
    fun provideSettingsRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): SettingsRepository {
        return AppSettingsRepository(networkClient, dataStoreManager)
    }
}