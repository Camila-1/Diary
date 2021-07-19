package com.pchpsky.diary.screens.launch

import com.pchpsky.schema.CurrentUserQuery

interface LaunchRepository {
    fun user(): CurrentUserQuery.Data?
}