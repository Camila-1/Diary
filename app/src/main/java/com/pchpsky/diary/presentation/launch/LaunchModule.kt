package com.pchpsky.diary.presentation.launch

import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.presentation.launch.interfaces.LaunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LaunchModule {

    @Provides
    fun provideLaunchRepository(networkClient: NetworkClient, dataStoreManager: DataStoreManager): LaunchRepository {
        return LaunchRepository(networkClient, dataStoreManager)
    }
}