package com.pchpsky.diary.domain.repositories

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation

interface LoginController {
    suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
}