package com.pchpsky.diary.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@Composable
fun TimePicker(show: Boolean, close: () -> Unit, selectTime: (LocalTime) -> Unit) {

    val timePickerDialogState = rememberMaterialDialogState()

    var selectedTime: LocalTime = LocalTime.now()

    MaterialDialog(
        dialogState = timePickerDialogState,
        buttons = {
            positiveButton("SELECT") {
                selectTime(selectedTime)
                close()
            }
            negativeButton("CANCEL") {
                close()
            }
        }
    ) {
        timepicker(
            title = "Select Injection Time"
        ) {
            selectedTime = it
        }
        if (show) {
            timePickerDialogState.show()
        }
    }
}

@Composable
@Preview
fun TimePickerPreview() {
    DiaryTheme {
        TimePicker(show = true, close = { /*TODO*/ }, selectTime = {} )
    }
}