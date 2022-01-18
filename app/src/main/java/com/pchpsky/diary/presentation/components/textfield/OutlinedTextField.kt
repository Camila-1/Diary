package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun OutlinedTextField(
    value: MutableState<String>,
    label: String,
    errorMessage: String?,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation
) {

    var isError by remember(errorMessage) { mutableStateOf(errorMessage != null) }

    Column {
        OutlinedTextField(
            value = value.value,
            onValueChange = {
                isError = false
                value.value = it
            },
            modifier = Modifier.fillMaxWidth(1f).height(60.dp)
                .semantics { contentDescription = "email_input_field" },
            textStyle = DiaryTheme.typography.textField,
            label = { Text(text = label, color = if (isError) DiaryTheme.colors.error else DiaryTheme.colors.inputText) },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = DiaryTheme.colors.focusedInputFieldBorder,
                unfocusedBorderColor = DiaryTheme.colors.unfocusedInputFieldBorder,
                errorLabelColor = DiaryTheme.colors.error,
                cursorColor = DiaryTheme.colors.unfocusedInputFieldBorder,
                textColor = DiaryTheme.colors.inputText
            ),
            isError = isError,
            singleLine = true,
            shape = DiaryTheme.shapes.roundedTextField,
            trailingIcon = {
                if (isError)
                    Icon(Icons.Filled.Error, "error_icon", tint = DiaryTheme.colors.error)
            },
        )
        Text(
            text = if (isError) errorMessage!! else "",
            color = DiaryTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp).testTag("error_message_text")
        )
    }
}

