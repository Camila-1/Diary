package com.pchpsky.diary.presentation.record

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.extensions.insulins
import com.pchpsky.diary.extensions.toHex
import com.pchpsky.diary.extensions.toValidDouble
import com.pchpsky.diary.presentation.record.insulin.RecordInsulinViewState
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val repository: RecordInsulinRepository
    ) : ViewModel(), RecordInsulinViewModel {

    private var _uiState = MutableStateFlow(RecordInsulinViewState())
    override val uiState: StateFlow<RecordInsulinViewState> = _uiState

    override fun incrementUnits() {
        val units = _uiState.value.units
        if (units == 100.0) return
        _uiState.value = _uiState.value.copy(units = units + 1)
    }

    override fun decrementUnits() {
        val units = _uiState.value.units
        if (units == 1.0) return
        _uiState.value = _uiState.value.copy(units = units - 1)
    }

    override fun setUnits(units: String) {
        val value = units.toValidDouble()
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
                val insulins = it.insulins()
                _uiState.value = _uiState.value.copy(insulins = insulins!!, selectedInsulin = insulins.first(), loading = false)
            }
        )
    }

    override fun selectInsulin(insulin: Insulin) {
        _uiState.value = _uiState.value.copy(dropDownInsulinMenu = false, selectedInsulin = insulin)
    }

    override fun dropInsulinMenu(drop: Boolean) {
        _uiState.value = _uiState.value.copy(dropDownInsulinMenu = drop)
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
}

val FakeRecordInsulinViewModel = object : RecordInsulinViewModel {
    override val uiState: StateFlow<RecordInsulinViewState> =
        MutableStateFlow(
            RecordInsulinViewState()
                .copy(selectedInsulin = Insulin("id", Color.Blue.toHex(), "Test Insulin"))
        )
    override fun decrementUnits() {}
    override fun incrementUnits() {}
    override fun setUnits(points: String) {}
    override suspend fun insulins() {}
    override fun selectInsulin(insulin: Insulin) {}
    override fun dropInsulinMenu(drop: Boolean) {}
    override fun showTimePicker(show: Boolean) {}
    override fun showDatePicker(show: Boolean) {}
    override fun selectTime(time: String) {}
    override fun selectDate(date: String) {}
}
