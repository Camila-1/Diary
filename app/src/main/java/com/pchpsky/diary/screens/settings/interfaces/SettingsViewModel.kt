package com.pchpsky.diary.screens.settings.interfaces

import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.GlucoseUnits
import kotlinx.coroutines.flow.StateFlow

interface SettingsViewModel {
    val insulins: StateFlow<List<Insulin>>
    val glucoseUnit: StateFlow<String>
    suspend fun settings()
    suspend fun updateGlucoseUnit(unit: GlucoseUnits)
}