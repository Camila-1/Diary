package com.pchpsky.diary.screens.settings

import arrow.core.Either
import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateInsulinMutation

class AppSettingsRepository(
    val networkClient: NetworkClient,
    dataStoreManager: DataStoreManager
) : SettingsRepository {

    suspend fun createInsulin(color: String, name: String): Either<NetworkError, CreateInsulinMutation.Data> {
        return networkClient.createInsulin(color, name)
    }
}