package com.pchpsky.diary.presentation.settings

import androidx.compose.ui.graphics.Color
import com.pchpsky.diary.data.network.model.Insulin

data class SettingsViewState(
    val insulins: List<Insulin> = listOf(),
    val glucoseUnit: String = "",
    var loading: Boolean = false,
    val addInsulinDialogStateState: AddInsulinDialogState =
        AddInsulinDialogState(
            show = false,
            insulinName = "",
            insulinColor = Color.Yellow
        ),
    val deleteInsulinDialogStateState: DeleteInsulinDialogState =
        DeleteInsulinDialogState(
            show = false,
            insulinId = ""
        ),
    val updateInsulinDialogStateState: UpdateInsulinDialogState =
        UpdateInsulinDialogState(
            show = false,
            insulinId = "",
            insulinName = "",
            insulinColor = Color.Yellow
        )
)

data class AddInsulinDialogState(
    val show: Boolean,
    val insulinName: String,
    val insulinColor: Color
)

data class UpdateInsulinDialogState(
    val show: Boolean,
    val insulinId: String,
    val insulinName: String,
    val insulinColor: Color
)

data class DeleteInsulinDialogState(
    val show: Boolean,
    val insulinId: String
)