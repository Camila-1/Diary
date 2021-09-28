package com.pchpsky.diary.screens.auth

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation

interface AuthRepository {

    suspend fun createUser(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
    suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?>
}