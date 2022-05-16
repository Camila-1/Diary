package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(show: Boolean, close: () -> Unit, selectDate: (String) -> Unit) {
    if (!show) return

    val datePickerDialogState = rememberMaterialDialogState()

    var selectedDate = ""

    MaterialDialog(
        dialogState = datePickerDialogState,
        buttons = {
            positiveButton("Select") {
                selectDate(selectedDate)
                close()
            }
            negativeButton("Cancel") {
                close()
            }
        },
        onCloseRequest = { close() }
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            datepicker(
                title = "Select Date"
            ) {
                selectedDate = it.format(DateTimeFormatter.ofPattern("dd MMM"))
            }
        }
    }
    datePickerDialogState.show()
}

@Composable
@Preview
fun DatePickerPreview() {
    DiaryTheme {
        DatePicker(show = true, close = {}, selectDate = {})
    }
}