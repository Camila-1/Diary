package com.pchpsky.diary.screens.launch

import arrow.core.Either
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery

interface LaunchRepository {
    fun user(): Either<NetworkError, CurrentUserQuery.Data?>
}