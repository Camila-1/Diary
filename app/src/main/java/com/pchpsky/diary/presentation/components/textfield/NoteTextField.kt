package com.pchpsky.diary.presentation.components.textfield

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.theme.DiaryTheme

@ExperimentalComposeUiApi
@Composable
fun NoteTextField(
    value: String,
    modifier: Modifier,
    onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${value.length}/280",
            modifier = Modifier
                .align(Alignment.End),
            color = DiaryTheme.colors.inputText
        )

        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= 280) onValueChanged(it)
            },
            modifier = Modifier
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
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
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

@ExperimentalComposeUiApi
@Composable
@Preview
fun NoteTextFieldPreview() {
    DiaryTheme {
        NoteTextField(value = "Added note", modifier = Modifier) {}
    }
}