package com.pchpsky.diary.presentation.recordinsulin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.data.repositories.interfacies.InsulinDataSource
import com.pchpsky.diary.presentation.recordinsulin.viewmodelinterface.RecordInsulinViewModel
import com.pchpsky.diary.utils.extensions.insulins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class InsulinViewModel @Inject constructor(
    private val repository: InsulinDataSource
    ) : ViewModel(),
    RecordInsulinViewModel {

    private var _uiState = MutableStateFlow(RecordInsulinViewState())
    override val uiState: StateFlow<RecordInsulinViewState> = _uiState

    override fun incrementUnits() {
        val units = _uiState.value.units
        if (units == 99.0) return
        _uiState.value = _uiState.value.copy(units = units + 1)
    }

    override fun decrementUnits() {
        val units = _uiState.value.units
        if (units == 1.0) return
        _uiState.value = _uiState.value.copy(units = units - 1)
    }

    override fun setUnits(units: Double) {
        val value = units
        if (value == null) _uiState.value = _uiState.value.copy(unitsInputError = "Units value is invalid")
        else if (value < 1.0 || value > 100.0) return
        else _uiState.value = _uiState.value.copy(units = value, unitsInputError = "")
    }

    override suspend fun insulins() {
        Log.d("debugInsulin", "insulins")
        _uiState.value = _uiState.value.copy(loading = true)
        repository.insulins().fold(
            ifLeft = {
                Log.d("debugInsulin", "returns left")
            },
            ifRight = {
                Log.d("debugInsulin", "returns right")
                val insulins = it.insulins()
                _uiState.value = _uiState.value.copy(insulins = insulins!!, selectedInsulin = insulins.first(), loading = false)
            }
        )
    }

    override fun selectInsulin(insulin: Insulin) {
        _uiState.value = _uiState.value.copy(showInsulinMenu = false, selectedInsulin = insulin)
    }

    override fun showInsulinMenu(show: Boolean) {
        _uiState.value = _uiState.value.copy(showInsulinMenu = show)
    }

    override fun showTimePicker(show: Boolean) {
        _uiState.value = _uiState.value.copy(showTimePicker = show)
    }

    override fun showDatePicker(show: Boolean) {
        _uiState.value = _uiState.value.copy(showDatePicker = show)
    }

    override fun selectTime(localTime: String) {
        _uiState.value = _uiState.value.copy(time = localTime)
    }

    override fun selectDate(localDate: String) {
        _uiState.value = _uiState.value.copy(date = localDate)
    }

    override fun addNote(note: String) {
        _uiState.value = _uiState.value.copy(note = note)
    }
}

