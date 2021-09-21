package com.pchpsky.diary.screens.launch

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery

interface LaunchViewModel {

    suspend fun userLoggedIn(): Either<NetworkError, CurrentUserQuery.Data?>

    suspend fun token(): String?
}