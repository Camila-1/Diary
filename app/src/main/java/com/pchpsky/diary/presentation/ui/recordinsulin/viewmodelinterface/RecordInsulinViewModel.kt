package com.pchpsky.diary.presentation.ui.recordinsulin.viewmodelinterface

import com.pchpsky.diary.data.entities.Insulin
import com.pchpsky.diary.presentation.recordinsulin.RecordInsulinViewState
import kotlinx.coroutines.flow.StateFlow

interface RecordInsulinViewModel {
    val uiState: StateFlow<RecordInsulinViewState>
    fun incrementUnits()
    fun decrementUnits()
    fun setUnits(units: Double)
    suspend fun insulins()
    fun selectInsulin(insulin: Insulin)
    fun showInsulinMenu(drop: Boolean)
    fun showTimePicker(show: Boolean)
    fun showDatePicker(show: Boolean)
    fun selectTime(localTime: String)
    fun selectDate(localDate: String)
    fun addNote(note: String)
}