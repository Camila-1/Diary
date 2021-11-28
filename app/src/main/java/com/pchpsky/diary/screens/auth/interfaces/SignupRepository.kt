package com.pchpsky.diary.screens.auth.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateUserMutation

interface SignupRepository {
    suspend fun createUser(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
}