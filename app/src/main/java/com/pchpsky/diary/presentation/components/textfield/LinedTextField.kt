package com.pchpsky.diary.presentation.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun LinedTextField(value: MutableState<String>, modifier: Modifier, placeHolder: String) {

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
        placeholder = {
            Text(
                text = placeHolder,
                color = DiaryTheme.colors.inputText,
                style = DiaryTheme.typography.textField
            )
        },
        singleLine = true,
        textStyle = DiaryTheme.typography.textField
    )
}