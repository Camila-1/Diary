package com.pchpsky.diary.presentation.record.insulin.interfacies

import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.record.insulin.RecordInsulinViewState
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime

interface RecordInsulinViewModel {
    val uiState: StateFlow<RecordInsulinViewState>
    fun incrementUnits()
    fun decrementUnits()
    fun setUnits(points: String)
    suspend fun insulins()
    fun selectInsulin(insulin: Insulin)
    fun dropInsulinMenu(drop: Boolean)
    fun showTimePicker(show: Boolean)
    fun showDatePicker(show: Boolean)
    fun setTime(localTime: String)
}