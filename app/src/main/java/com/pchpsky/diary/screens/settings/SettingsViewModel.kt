package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettings
import com.pchpsky.diary.screens.settings.interfaces.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

enum class GlucoseUnits{
    MG_PER_DL,
    MMOL_PER_L
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel(), InsulinSettings, Settings {

    private var _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.None)

    override val uiState: StateFlow<SettingsState> = _uiState

    override suspend fun addInsulin(color: String, name: String) {
        repository.createInsulin(color, name).fold(
            {

            },
            {
                repository.saveInsulin(it.insulin?.id!!, it.insulin.color!!, it.insulin.name!!)
                _uiState.value = SettingsState.InsulinAdded(it.insulin.color, it.insulin.name)
            }
        )
    }
}

object FakeSettingsViewModel : InsulinSettings, Settings {
    override val uiState: StateFlow<SettingsState> = MutableStateFlow(SettingsState.None)
    override suspend fun addInsulin(color: String, name: String) {}
}