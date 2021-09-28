package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.composables.ColorPicker
import com.pchpsky.diary.screens.settings.AppSettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.blue
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun InsulinSettings() {

    val viewModel: AppSettingsViewModel = hiltViewModel()

    val insulinName = remember { mutableStateOf("") }
    val insulinColor = remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background)
    ) {
        AddInsulin(insulinName, insulinColor)
    }
}

@Composable
fun AddInsulin(name: MutableState<String>, color: MutableState<Color>) {

    val dialogState = rememberMaterialDialogState()
    ColorPicker(dialogState, color)

    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .background(color.value, CircleShape)
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    dialogState.show()
                }
        )

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blue,
                unfocusedBorderColor = Color.White,
                textColor = Color.White,
                cursorColor = Color.White
            ),
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

@Preview
@Composable
fun InsulinSettingsPreview() {
    Settings(rememberNavController())
}
