package com.pchpsky.diary.screens.settings.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.SettingsQuery

interface SettingsRepository {

    suspend fun settings(): Either<NetworkError, SettingsQuery.Data>
    suspend fun updateGlucoseUnit(unit: String)
}