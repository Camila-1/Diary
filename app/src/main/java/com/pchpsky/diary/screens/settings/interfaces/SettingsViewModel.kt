package com.pchpsky.diary.screens.settings.interfaces

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.pchpsky.diary.screens.settings.GlucoseUnits
import com.pchpsky.diary.screens.settings.SettingsViewState
import kotlinx.coroutines.flow.StateFlow

interface SettingsViewModel {
    val uiState: StateFlow<SettingsViewState>
    suspend fun settings()
    suspend fun updateGlucoseUnit(unit: GlucoseUnits)
    suspend fun addInsulin(color: String, name: String)
    fun showAddInsulinDialog(show: Boolean, name: String = "", color: Color = Color(Color.Yellow.toArgb()))
}