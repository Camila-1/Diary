package com.pchpsky.diary.screens.settings.interfaces

import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.SettingsState
import kotlinx.coroutines.flow.StateFlow

interface InsulinSettingsViewModel {
    val insulins: StateFlow<List<Insulin>>
    val uiState: StateFlow<SettingsState>

    suspend fun addInsulin(color: String, name: String)
}