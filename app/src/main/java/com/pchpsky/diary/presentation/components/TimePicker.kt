package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePicker(show: Boolean, close: () -> Unit, selectTime: (String) -> Unit) {
    if (!show) return

    val timePickerDialogState = rememberMaterialDialogState()

    var selectedTime = ""

    MaterialDialog(
        dialogState = timePickerDialogState,
        buttons = {
            positiveButton("Select") {
                selectTime(selectedTime)
                close()
            }
            negativeButton("Cancel") {
                close()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            timepicker(
                title = "Select Injection Time"
            ) {
                selectedTime = it.format(DateTimeFormatter.ofPattern("hh:mm"))
            }
        }
    }
    timePickerDialogState.show()
}

@Composable
@Preview
fun TimePickerPreview() {
    DiaryTheme {
        TimePicker(show = true, close = {}, selectTime = {} )
    }
}