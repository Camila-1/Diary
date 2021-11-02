package com.pchpsky.diary.screens.settings.interfaces

import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.GlucoseUnits
import com.pchpsky.diary.screens.settings.SettingsViewState
import kotlinx.coroutines.flow.StateFlow

interface SettingsViewModel {
    val uiState: StateFlow<SettingsViewState>
    suspend fun settings()
    suspend fun updateGlucoseUnit(unit: GlucoseUnits)
    suspend fun addInsulin(color: String, name: String)
}