package com.pchpsky.diary.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun LinedTextField(value: MutableState<String>, modifier: Modifier) {
    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DiaryTheme.colors.focusedInputFieldBorder,
            unfocusedBorderColor = DiaryTheme.colors.unfocusedInputFieldBorder,
            textColor = DiaryTheme.colors.inputText,
            cursorColor = DiaryTheme.colors.inputText
        ),
        modifier = modifier,
        placeholder = { Text(text = "Name", color = DiaryTheme.colors.inputText) }
    )
}