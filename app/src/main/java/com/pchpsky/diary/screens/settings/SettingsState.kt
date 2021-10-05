package com.pchpsky.diary.screens.settings

import com.pchpsky.diary.datasource.network.model.Insulin

sealed class SettingsState() {
    object None : SettingsState()
    data class InsulinAdded(val insulin: Insulin) : SettingsState()
}
