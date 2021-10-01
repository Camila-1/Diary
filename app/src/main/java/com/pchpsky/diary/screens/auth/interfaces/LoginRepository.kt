package com.pchpsky.diary.screens.auth.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation

interface LoginRepository {
    suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
}