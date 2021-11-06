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
            insulinColor = Color.Yellow
        ),
    var deleteInsulinDialog: DeleteInsulinDialog =
        DeleteInsulinDialog(
            show = false,
            insulinId = ""
        ),
    var updateInsulinDialog: UpdateInsulinDialog =
        UpdateInsulinDialog(
            show = false,
            insulinId = "",
            insulinName = "",
            insulinColor = Color.Yellow
        )
)

data class EditInsulinDialog(
    val show: Boolean,
    val insulinName: String,
    val insulinColor: Color
)

data class UpdateInsulinDialog(
    val show: Boolean,
    val insulinId: String,
    val insulinName: String,
    val insulinColor: Color
)

data class DeleteInsulinDialog(
    val show: Boolean,
    val insulinId: String
)