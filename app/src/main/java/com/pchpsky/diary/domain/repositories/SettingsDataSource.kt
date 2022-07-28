package com.pchpsky.diary.domain.repositories

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.*

interface SettingsDataSource {
    suspend fun createInsulin(color: String, name: String): Either<NetworkError, CreateInsulinMutation.Data>
    suspend fun insulins(): Either<NetworkError, InsulinsQuery.Data>
    suspend fun settings(): Either<NetworkError, SettingsQuery.Data>
    suspend fun updateGlucoseUnit(unit: String): Either<NetworkError, UpdateSettingsMutation.Data>
    suspend fun deleteInsulin(id: String): Either<NetworkError, DeleteInsulinMutation.Data>
    suspend fun updateInsulin(id: String, name: String, color: String): Either<NetworkError, UpdateInsulinMutation.Data>
}