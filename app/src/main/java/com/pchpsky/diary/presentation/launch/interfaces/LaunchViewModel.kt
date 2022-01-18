package com.pchpsky.diary.presentation.launch.interfaces

import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery

interface LaunchViewModel {

    suspend fun userLoggedIn(): Either<NetworkError, CurrentUserQuery.Data?>

    suspend fun token(): String?
}