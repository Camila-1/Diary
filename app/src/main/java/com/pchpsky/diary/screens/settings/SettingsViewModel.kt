package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.extensions.insulin
import com.pchpsky.diary.extensions.insulins
import com.pchpsky.diary.screens.settings.interfaces.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

enum class GlucoseUnits(val unit: String){
    MG_PER_DL("mg/dL"),
    MMOL_PER_L("mmol/gL");

    companion object {
        val units: List<GlucoseUnits> = listOf(MG_PER_DL, MMOL_PER_L)
    }
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
    ) : ViewModel(),
    SettingsViewModel {

    private var _uiState = MutableStateFlow(SettingsViewState())
    override val uiState: StateFlow<SettingsViewState> = _uiState

    override suspend fun addInsulin(color: String, name: String) {
        repository.createInsulin(color, name).fold(
            {

            },
            {
                _uiState.value = _uiState.value.copy(_uiState.value.insulins + it.insulin())
            }
        )
    }

    override suspend fun settings() {
        _uiState.value.loading = true
        repository.settings().fold(
            {},
            {
                val glucoseUnit = when(it.settings?.bloodGlucoseUnits?.rawValue!!) {
                    GlucoseUnits.MMOL_PER_L.name -> GlucoseUnits.MMOL_PER_L.unit
                    GlucoseUnits.MG_PER_DL.name -> GlucoseUnits.MG_PER_DL.unit
                    else -> {""}
                }
                val insulins = it.insulins()!!
                _uiState.value = _uiState.value.copy(insulins, glucoseUnit, false)
            }
        )
    }

    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {
        repository.updateGlucoseUnit(unit.name)
    }
}

object FakeSettingsViewModel : SettingsViewModel {
    override val uiState: StateFlow<SettingsViewState> = MutableStateFlow(SettingsViewState())
    override suspend fun addInsulin(color: String, name: String) {}
    override suspend fun settings() {}
    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {}
}