package com.pchpsky.diary.presentation.record.insulin

import com.pchpsky.diary.data.network.model.Insulin
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class RecordInsulinViewState(
    val units: Double = 1.0,
    val selectedInsulin: Insulin? = null,
    val insulins: List<Insulin> = listOf(),
    val unitsInputError: String = "",
    val dropDownInsulinMenu: Boolean = false,
    val showTimePicker: Boolean = false,
    val time: String = LocalTime.now().format(DateTimeFormatter.ISO_TIME)
)
