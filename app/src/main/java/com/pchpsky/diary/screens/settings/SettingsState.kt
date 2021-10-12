package com.pchpsky.diary.screens.settings

import com.pchpsky.diary.datasource.network.model.Insulin

sealed class SettingsState() {
    object None : SettingsState()
    object Loading : SettingsState()
    data class InsulinAdded(val insulin: Insulin) : SettingsState()
    data class Settings(val insulins: List<Insulin>, val glucoseInit: String) : SettingsState()
}
