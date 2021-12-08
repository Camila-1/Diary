package com.pchpsky.diary.screens.launch.interfaces

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun user(): Either<NetworkError, CurrentUserQuery.Data?>

    fun token(): Flow<String?>
}