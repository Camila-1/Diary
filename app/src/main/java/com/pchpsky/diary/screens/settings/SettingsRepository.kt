package com.pchpsky.diary.screens.settings

import arrow.core.Either
import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.screens.settings.interfaces.CurrentSettingsRepository
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettingsRepository
import com.pchpsky.schema.CreateInsulinMutation

class SettingsRepository(
    val networkClient: NetworkClient,
    dataStoreManager: DataStoreManager
) : CurrentSettingsRepository, InsulinSettingsRepository {

    override suspend fun createInsulin(color: String, name: String): Either<NetworkError, CreateInsulinMutation.Data> {
        return networkClient.createInsulin(color, name)
    }

    override fun saveInsulin(id: String, color: String, name: String) {

    }
}