package com.pchpsky.diary.screens.settings

sealed class SettingsState() {
    object None : SettingsState()
    data class InsulinAdded(val id: String, val color: String, val name: String) : SettingsState()
}
