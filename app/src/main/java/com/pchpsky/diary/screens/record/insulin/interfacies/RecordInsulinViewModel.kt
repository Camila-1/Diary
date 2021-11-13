package com.pchpsky.diary.screens.record.insulin.interfacies

import com.pchpsky.diary.screens.record.insulin.RecordInsulinViewState
import kotlinx.coroutines.flow.StateFlow

interface RecordInsulinViewModel {
    val uiState: StateFlow<RecordInsulinViewState>
    fun incrementUnits()
    fun decrementUnits()
    fun setUnits(points: String)
}