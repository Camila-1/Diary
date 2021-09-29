package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.composables.ColorPicker
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettings
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.blue
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@Composable
fun InsulinSettings(viewModel: InsulinSettings) {


    val insulinName = remember { mutableStateOf("") }
    val insulinColor = remember { mutableStateOf(Color.White) }
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
    ) {

        AddInsulin(insulinName, insulinColor) {
            scope.launch {
                viewModel.addInsulin(insulinColor.value.toString(), insulinName.value)
            }
        }
    }
}

@Composable
fun AddInsulin(name: MutableState<String>, color: MutableState<Color>, onAddClick: () -> Unit) {

    val dialogState = rememberMaterialDialogState()
    ColorPicker(dialogState, color)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 20.dp, end = 0.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Box(
            modifier = Modifier
                .background(color.value, CircleShape)
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    dialogState.show()
                }
                .align(Alignment.CenterVertically)
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
            modifier = Modifier.padding(start = 10.dp)
        )

        IconButton(
            onClick = onAddClick,
            modifier = Modifier.then(Modifier.size(50.dp)).align(Alignment.CenterVertically)
        ) {
            Icon(
                Icons.Filled.Add,
                "",
                tint = Color.White,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}

@Preview
@Composable
fun InsulinSettingsPreview() {
    DiaryTheme {
        InsulinSettings(FakeSettingsViewModel)
    }
}
