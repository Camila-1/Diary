package com.pchpsky.diary.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.screens.theme.blue

@Composable
fun TextField(value: MutableState<String>, label: String, errorMessage: String?) {

    var isError by remember(errorMessage) { mutableStateOf(errorMessage != null) }

    OutlinedTextField(
        value = value.value,
        onValueChange = {
            isError = false
            value.value = it
        },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp)
            .semantics { contentDescription = "email_input_field" },

        textStyle = TextStyle(color = Color.White),
        label = { Text(text = label, color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        ),
        isError = isError,
        singleLine = true,
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colors.error)
        }
    )
}