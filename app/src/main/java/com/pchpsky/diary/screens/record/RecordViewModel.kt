package com.pchpsky.diary.screens.record

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.extensions.toValidDouble
import com.pchpsky.diary.screens.record.insulin.RecordInsulinViewState
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinRepository
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinViewModel
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
        val points = _uiState.value.units
        if (points == 100.0) return
        _uiState.value = _uiState.value.copy(units = points + 1)
    }

    override fun decrementUnits() {
        val points = _uiState.value.units
        if (points == 1.0) return
        _uiState.value = _uiState.value.copy(units = points - 1)
    }

    override fun setUnits(points: String) {
        val value = points.toValidDouble()
        if (value == null) _uiState.value = _uiState.value.copy(unitsInputError = "Units value is invalid")
        else if (value <= 0.0 || value >= 100.0) return
        else _uiState.value = _uiState.value.copy(units = value, unitsInputError = "")
    }
}

val FakeRecordInsulinViewModel = object : RecordInsulinViewModel {
    override val uiState: StateFlow<RecordInsulinViewState> = MutableStateFlow(RecordInsulinViewState())
    override fun decrementUnits() {}
    override fun incrementUnits() {}
    override fun setUnits(points: String) {}
}
