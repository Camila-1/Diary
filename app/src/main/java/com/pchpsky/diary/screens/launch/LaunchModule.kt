package com.pchpsky.diary.screens.launch

import com.pchpsky.diary.datasource.localstorage.DataStoreManager
import com.pchpsky.diary.datasource.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LaunchModule {

    @Provides
    fun provideLaunchRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): LaunchRepository {
        return AppLaunchRepository(networkClient, dataStoreManager)
    }
}