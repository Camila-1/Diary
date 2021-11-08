package com.pchpsky.diary.screens.settings

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import arrow.core.extensions.list.foldable.find
import arrow.core.getOrElse
import com.pchpsky.diary.extensions.insulin
import com.pchpsky.diary.extensions.insulins
import com.pchpsky.diary.screens.settings.interfaces.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

enum class GlucoseUnits(val unit: String) {
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
                _uiState.value.insulins.toMutableList().apply { add(it.insulin()) }
                _uiState.value = _uiState.value.copy()
            }
        )
    }

    override suspend fun settings() {
        Log.d("Settings", "Settings")
        _uiState.value.loading = true
        repository.settings().fold(
            {
                Log.d("Settings", it.toString())
            },
            {
                Log.d("Settings", "right")
                val glucoseUnit = when (it.settings?.bloodGlucoseUnits?.rawValue!!) {
                    GlucoseUnits.MMOL_PER_L.name -> GlucoseUnits.MMOL_PER_L.unit
                    GlucoseUnits.MG_PER_DL.name -> GlucoseUnits.MG_PER_DL.unit
                    else -> {
                        ""
                    }
                }
                val insulins = it.insulins()!!
                _uiState.value = _uiState.value.copy(
                    insulins = insulins,
                    glucoseUnit = glucoseUnit,
                    loading = false)
                _uiState.value.loading = false
            }
        )
    }

    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {
        repository.updateGlucoseUnit(unit.name).fold(
            ifLeft = {},
            ifRight = { data ->
                val newUnit = data.settings?.bloodGlucoseUnits?.name!!

                _uiState.value = _uiState.value.copy( glucoseUnit =
                    if(GlucoseUnits.MMOL_PER_L.name == newUnit) GlucoseUnits.MMOL_PER_L.unit
                    else GlucoseUnits.MG_PER_DL.unit
                )
            }
        )
    }

    override suspend fun updateInsulin(id: String, name: String, color: String) {
        repository.updateInsulin(id, name, color).fold(
            ifLeft = {},
            ifRight = { data ->
                val index = _uiState.value.insulins.indexOf(_uiState.value.insulins
                        .find { it.id == data.insulin?.id!! }
                        .getOrElse { 0 }
                    )

                _uiState.value = _uiState.value.copy(
                    insulins = _uiState.value.insulins.apply { toMutableList()[index] = data.insulin() }.toList()
                    //mutableListOf<Insulin>().plus(_uiState.value.insulins).plus(data.insulin()).toList()
                )
            }
        )
    }

    override fun showAddInsulinDialog(show: Boolean, name: String, color: Color) {
        _uiState.value =
            _uiState.value.copy(addInsulinDialogStateState = AddInsulinDialogState(show, name, color))
    }

    override fun showDeleteInsulinDialog(show: Boolean, id: String) {
        _uiState.value = _uiState.value.copy(deleteInsulinDialogStateState = DeleteInsulinDialogState(show, id))
    }

    override fun showUpdateInsulinDialog(show: Boolean, id: String, name: String, color: Color) {
        _uiState.value = _uiState.value
            .copy(updateInsulinDialogStateState = UpdateInsulinDialogState(show, id, name, color))
    }

    override suspend fun deleteInsulin(id: String) {
        repository.deleteInsulin(id).fold(
            ifLeft = {

            },
            ifRight = { data ->
                val newList = _uiState.value.insulins.apply {
                    toMutableList().removeIf { it.id == data.insulin?.id }
                }.toList()
                _uiState.value = _uiState.value.copy(insulins = newList)

            }
        )
    }
}

object FakeSettingsViewModel : SettingsViewModel {
    override val uiState: StateFlow<SettingsViewState> = MutableStateFlow(SettingsViewState())
    override suspend fun addInsulin(color: String, name: String) {}
    override suspend fun deleteInsulin(id: String) {}
    override suspend fun updateInsulin(id: String, name: String, color: String) {}
    override suspend fun settings() {}
    override suspend fun updateGlucoseUnit(unit: GlucoseUnits) {}
    override fun showAddInsulinDialog(show: Boolean, name: String, color: Color) {}
    override fun showDeleteInsulinDialog(show: Boolean, id: String) {}
    override fun showUpdateInsulinDialog(show: Boolean, id: String, name: String, color: Color) {}
}