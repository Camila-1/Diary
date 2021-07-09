package com.pchpsky.diary.ui.launch

import com.pchpsky.schema.CurrentUserQuery

interface LaunchRepository {
    suspend fun user(): CurrentUserQuery.Data?
}