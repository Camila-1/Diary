package com.pchpsky.diary.domain.repositories

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.CreateUserMutation

interface SignupController {
    suspend fun createUserAndSaveUserToken(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?>
}