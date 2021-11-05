package com.pchpsky.diary.screens.settings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.pchpsky.diary.datasource.network.model.Insulin

data class SettingsViewState(
    var insulins: MutableList<Insulin> = mutableListOf(),
    var glucoseInit: String = "",
    var loading: Boolean = false,
    var editInsulinDialog: EditInsulinDialog =
        EditInsulinDialog(
            show = false,
            insulinName = "",
            insulinColor = Color(Color.Yellow.toArgb())
        ),
    var deleteInsulinDialog: DeleteInsulinDialog =
        DeleteInsulinDialog(
            show = false,
            insulinId = ""
        )
)

data class EditInsulinDialog(
    val show: Boolean,
    val insulinName: String,
    val insulinColor: Color
)

data class DeleteInsulinDialog(
    val show: Boolean,
    val insulinId: String
)