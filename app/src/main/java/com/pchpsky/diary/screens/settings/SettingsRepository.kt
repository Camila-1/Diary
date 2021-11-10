package com.pchpsky.diary.screens.settings

import arrow.core.Either
import com.pchpsky.diary.datasource.localstorage.DataStoreManager
import com.pchpsky.diary.datasource.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.screens.settings.interfaces.SettingsRepository
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettingsRepository
import com.pchpsky.schema.CreateInsulinMutation
import com.pchpsky.schema.InsulinsQuery
import com.pchpsky.schema.SettingsQuery
import com.pchpsky.schema.UpdateSettingsMutation

class SettingsRepository(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
) : SettingsRepository,
    InsulinSettingsRepository {

    override suspend fun createInsulin(
        color: String,
        name: String
    ): Either<NetworkError, CreateInsulinMutation.Data> = networkClient.createInsulin(color, name)

    override suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data> = networkClient.insulins()

    override suspend fun settings(): Either<NetworkError, SettingsQuery.Data> = networkClient.settings()

    override suspend fun updateGlucoseUnit(unit: String): Either<NetworkError, UpdateSettingsMutation.Data>
    = networkClient.updateGlucoseUnit(unit)

    override suspend fun updateInsulin(id: String, name: String, color: String) =
        networkClient.updateInsulin(id, color, name)

    override suspend fun deleteInsulin(id: String) = networkClient.deleteInsulin(id)
}