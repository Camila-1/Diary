package com.pchpsky.diary.screens.settings.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.DeleteInsulinMutation
import com.pchpsky.schema.SettingsQuery
import com.pchpsky.schema.UpdateInsulinMutation
import com.pchpsky.schema.UpdateSettingsMutation

interface SettingsRepository {

    suspend fun settings(): Either<NetworkError, SettingsQuery.Data>
    suspend fun updateGlucoseUnit(unit: String): Either<NetworkError, UpdateSettingsMutation.Data>
    suspend fun deleteInsulin(id: String):  Either<NetworkError, DeleteInsulinMutation.Data>
    suspend fun updateInsulin(id: String, name: String, color: String): Either<NetworkError, UpdateInsulinMutation.Data>
}