package com.pchpsky.diary.screens.auth.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation

interface AuthController {
    suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
    suspend fun createUserAndSaveUserToken(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
}