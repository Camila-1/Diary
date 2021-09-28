package com.pchpsky.diary.screens.settings

import com.pchpsky.diary.datasourse.network.NetworkClient

class AppSettingsRepository(
    val networkClient: NetworkClient
) : SettingsRepository {
}