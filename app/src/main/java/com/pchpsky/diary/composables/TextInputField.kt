package com.pchpsky.diary.composables

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.blue

@Composable
fun TextField(
    value: MutableState<String>,
    label: String,
    errorMessage: String?,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation
) {

    var isError by remember(errorMessage) { mutableStateOf(errorMessage != null) }

    Column() {
        OutlinedTextField(
            value = value.value,
            onValueChange = {
                isError = false
                value.value = it
            },
            modifier = Modifier.fillMaxWidth(1f).height(60.dp)
                .semantics { contentDescription = "email_input_field" },
            textStyle = TextStyle(color = DiaryTheme.colors.grey),
            label = { Text(text = label, color = if (isError) DiaryTheme.colors.error else DiaryTheme.colors.grey) },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = DiaryTheme.colors.focusedInputFieldBorder,
                unfocusedBorderColor = DiaryTheme.colors.grey,
                errorLabelColor = Color.Red,
                cursorColor = DiaryTheme.colors.grey
            ),
            isError = isError,
            singleLine = true,
            shape = DiaryTheme.shapes.small,
            trailingIcon = {
                if (isError)
                    Icon(Icons.Filled.Error, "error", tint = DiaryTheme.colors.error)
            },
        )
        Text(
            text = if (isError) errorMessage!! else "",
            color = DiaryTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

