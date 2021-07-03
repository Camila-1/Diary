package com.pchpsky.diary.screens.launch

import com.pchpsky.schema.CurrentUserQuery

interface LaunchRepository {
    suspend fun user(): CurrentUserQuery.Data?
}