package com.pchpsky.diary.screens.settings.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateInsulinMutation

interface InsulinSettingsRepository {
    suspend fun createInsulin(color: String, name: String): Either<NetworkError, CreateInsulinMutation.Data>
}