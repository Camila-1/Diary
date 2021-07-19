package com.pchpsky.diary.screens.launch

import com.pchpsky.diary.datasourse.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LaunchModule {

    @Singleton
    @Provides
    fun provideLaunchRepository(networkClient: NetworkClient): LaunchRepository {
        return AppLaunchRepository(networkClient)
    }
}