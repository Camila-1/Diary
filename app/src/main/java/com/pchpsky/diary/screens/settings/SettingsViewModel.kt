package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.extensions.insulin
import com.pchpsky.diary.extensions.insulins
import com.pchpsky.diary.screens.settings.interfaces.InsulinViewModel
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
    ) : ViewModel(), InsulinViewModel,
    SettingsViewModel {

    override var insulins: List<Insulin> = emptyList()

    private var _glucoseUnit: MutableStateFlow<String> = MutableStateFlow("")
    override val glucoseUnit: StateFlow<String> = _glucoseUnit

    private var _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.None)
    override val uiState: StateFlow<SettingsState> = _uiState

    override suspend fun addInsulin(color: String, name: String) {
        repository.createInsulin(color, name).fold(
            {

            },
            {
                _uiState.value = SettingsState.Insulins(insulins + it.insulin())
            }
        )
    }

    override suspend fun insulins() {
        repository.insulins().fold(
            {

            },
            {
                insulins = it.insulins()!!
                _uiState.value = SettingsState.Insulins(insulins)
            }
        )
    }

    override suspend fun settings() {
        _uiState.value = SettingsState.Loading
        repository.settings().fold(
            {},
            {
                val glucoseUnit = when(it.settings?.bloodGlucoseUnits?.rawValue!!) {
                    GlucoseUnits.MMOL_PER_L.name -> GlucoseUnits.MMOL_PER_L.unit
                    GlucoseUnits.MG_PER_DL.name -> GlucoseUnits.MG_PER_DL.unit
                    else -> {""}
                }
                val insulins = it.insulins()!!
                _uiState.value = SettingsState.Settings(insulins, glucoseUnit)
            }
        )
    }

    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {
        repository.updateGlucoseUnit(unit.name)
    }
}

object FakeSettingsViewModel : InsulinViewModel,
    SettingsViewModel {
    override var insulins: List<Insulin> = emptyList()
    override val uiState: StateFlow<SettingsState> = MutableStateFlow(SettingsState.None)
    override suspend fun addInsulin(color: String, name: String) {}
    override suspend fun insulins() {}
    override val glucoseUnit: StateFlow<String> = MutableStateFlow("mmol/gL")
    override suspend fun settings() {}
    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {}
}