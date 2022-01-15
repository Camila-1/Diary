package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun NoteTextField(
    value: MutableState<String>,
    modifier: Modifier
) {

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${value.value.length}/280",
            modifier = Modifier
                .align(Alignment.End),
            color = DiaryTheme.colors.inputText
        )

        OutlinedTextField(
            value = value.value,
            onValueChange = {
                if (it.length <= 280) value.value = it
            },
            modifier = modifier
                .semantics { contentDescription = "email_input_field" }
                .requiredHeightIn(min = 60.dp, max = 150.dp)
                .fillMaxWidth(1f),
            textStyle = DiaryTheme.typography.textField,
            placeholder = {
                Text(
                    text = "Add Note",
                    color = DiaryTheme.colors.inputText,
                    style = DiaryTheme.typography.textField
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            shape = DiaryTheme.shapes.roundedTextField,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = DiaryTheme.colors.unfocusedInputFieldBorder,
                errorLabelColor = DiaryTheme.colors.error,
                cursorColor = DiaryTheme.colors.unfocusedInputFieldBorder,
                textColor = DiaryTheme.colors.inputText
            )
        )
    }

}

@Composable
@Preview
fun NoteTextFieldPreview() {
    DiaryTheme {
        NoteTextField(value = remember { mutableStateOf("") }, modifier = Modifier)
    }
}