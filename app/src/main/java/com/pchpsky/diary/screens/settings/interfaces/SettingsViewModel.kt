package com.pchpsky.diary.screens.settings.interfaces

import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.GlucoseUnits
import com.pchpsky.diary.screens.settings.SettingsState
import kotlinx.coroutines.flow.StateFlow

interface SettingsViewModel {
    val insulins: List<Insulin>
    val glucoseUnit: StateFlow<String>
    val uiState: StateFlow<SettingsState>
    suspend fun settings()
    suspend fun updateGlucoseUnit(unit: GlucoseUnits)
}