package com.pchpsky.diary.screens.record

import androidx.lifecycle.ViewModel
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

    override fun incrementPoints() {
        val points = _uiState.value.points
        _uiState.value = _uiState.value.copy(points = points + 1)
    }

    override fun decrementPoints() {
        val points = _uiState.value.points
        _uiState.value = _uiState.value.copy(points = points - 1)
    }

    override fun setPoints(points: Double) {
        _uiState.value = _uiState.value.copy(points = points)
    }
}
