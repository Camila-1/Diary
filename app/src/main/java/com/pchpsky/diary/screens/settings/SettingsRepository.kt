package com.pchpsky.diary.screens.settings

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateInsulinMutation

interface SettingsRepository {
    suspend fun createInsulin(color: String, name: String): Either<NetworkError, CreateInsulinMutation.Data>
    fun saveInsulin(id: String, color: String, name: String)
}