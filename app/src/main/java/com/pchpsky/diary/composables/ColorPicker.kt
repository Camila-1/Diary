package com.pchpsky.diary.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser

@Composable
fun ColorPicker(dialogState: MaterialDialogState, color: MutableState<Color>) {
    MaterialDialog(
        dialogState = dialogState,
        backgroundColor = Color.Black,
        buttons = {
            positiveButton(
                text = "Select",
                onClick = {

                }
            )
            negativeButton(
                text = "Cancel",
            )
        }
    ) {

        colorChooser(
            colors = ColorPalette.Primary,
            onColorSelected = { color.value = it }
        )
    }
}