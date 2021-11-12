package com.pchpsky.diary.screens.record.insulin

import com.pchpsky.diary.datasource.network.model.Insulin

data class RecordInsulinViewState(
    val points: Double = 1.0,
    val insulins: List<Insulin> = listOf(),
    val pointsInputError: String = ""
)
