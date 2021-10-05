package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.extensions.insulin
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

    private var _insulins: MutableStateFlow<List<Insulin>> = MutableStateFlow(emptyList())

    override val insulins: StateFlow<List<Insulin>> = _insulins

    private var _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.None)

    override val uiState: StateFlow<SettingsState> = _uiState

    override suspend fun addInsulin(color: String, name: String) {
        repository.createInsulin(color, name).fold(
            {

            },
            {
                _insulins.value = _insulins.value + it.insulin()
            }
        )
    }
}

object FakeSettingsViewModel : InsulinSettingsViewModel,
    CurrentSettingsViewModel {
    override val insulins: StateFlow<List<Insulin>> = MutableStateFlow(emptyList())
    override val uiState: StateFlow<SettingsState> = MutableStateFlow(SettingsState.None)
    override suspend fun addInsulin(color: String, name: String) {}
}