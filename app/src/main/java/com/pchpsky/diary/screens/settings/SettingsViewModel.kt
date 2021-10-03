package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettingsViewModel
import com.pchpsky.diary.screens.settings.interfaces.CurrentSettingsViewModel
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
) : ViewModel(), InsulinSettingsViewModel,
    CurrentSettingsViewModel {

    private var _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.None)

    override val uiState: StateFlow<SettingsState> = _uiState

    override suspend fun addInsulin(color: String, name: String) {
        repository.createInsulin(color, name).fold(
            {

            },
            {
                val insulin = it.insulin?.color
//                _uiState.value = SettingsState.InsulinAdded(it.insulin.color, it.insulin.name)
            }
        )
    }


}

object FakeSettingsViewModel : InsulinSettingsViewModel,
    CurrentSettingsViewModel {
    override val uiState: StateFlow<SettingsState> = MutableStateFlow(SettingsState.None)
    override suspend fun addInsulin(color: String, name: String) {}
}