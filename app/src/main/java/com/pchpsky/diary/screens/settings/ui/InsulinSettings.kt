package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.composables.ColorPicker
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@Composable
fun InsulinSettings(viewModel: InsulinSettingsViewModel) {


    val insulinName = remember { mutableStateOf("") }
    val insulinColor = remember { mutableStateOf(Color.Yellow) }
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
    ) {

        Box(
            modifier = Modifier
                .background(color.value, CircleShape)
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    dialogState.show()
                }
                .align(Alignment.Bottom)
        )

        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = DiaryTheme.colors.focusedInputFieldBorder,
                unfocusedBorderColor = DiaryTheme.colors.grey,
                textColor = DiaryTheme.colors.text,
                cursorColor = DiaryTheme.colors.text
            ),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(10f),
            placeholder = { Text(text = "Name", color = DiaryTheme.colors.grey) }
        )

        Button(
            onClick = onAddClick,
            modifier = Modifier
                .height(35.dp)
                .align(Alignment.Bottom)
                .weight(3f),
            border = BorderStroke(1.dp, DiaryTheme.colors.grey),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ),

        ) {
            Text(
                text = "Add",
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun InsulinList(insulins: List<Insulin>) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        insulins.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(it.color.red, it.color.green, it.color.blue), CircleShape)
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.Bottom)
                )
            }
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
