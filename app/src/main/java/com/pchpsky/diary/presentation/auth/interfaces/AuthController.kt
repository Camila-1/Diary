package com.pchpsky.diary.presentation.auth.interfaces

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation

interface AuthController {
    suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
    suspend fun createUserAndSaveUserToken(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
}