package com.pchpsky.diary.screens.settings.ui

import android.graphics.Color.parseColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.composables.ColorPicker
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.extensions.toHex
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.SettingsState
import com.pchpsky.diary.screens.settings.interfaces.InsulinSettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@Composable
fun InsulinSettings(viewModel: InsulinSettingsViewModel) {


    val insulinName = remember { mutableStateOf("") }
    val insulinColor = remember { mutableStateOf(Color(Color.Yellow.toArgb()) )}
    val scope = rememberCoroutineScope()
    val uiState: SettingsState by viewModel.uiState.collectAsState()
    val insulins by viewModel.insulins.collectAsState()

    scope.launch {
        viewModel.insulins()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
    ) {

        AddInsulin(insulinName, insulinColor) {
            scope.launch {
                viewModel.addInsulin(insulinColor.value.toHex(), insulinName.value)
                insulinName.value = ""
                insulinColor.value = Color(Color.Yellow.toArgb())
            }
        }

        InsulinList(insulins)
    }
}

@Composable
fun AddInsulin(name: MutableState<String>, color: MutableState<Color>, onAddClick: () -> Unit) {

    val dialogState = rememberMaterialDialogState()
    ColorPicker(dialogState, color)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
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

    val listState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxWidth(),
        state = listState
    ) {

        item {
            Text(
                text = "Insulins",
                color = Color.White,
                style = DiaryTheme.typography.h6
            )
        }

        items(insulins) { insulin ->
            Card(
                backgroundColor = Color(0xff333333),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(parseColor(insulin.color)), CircleShape)
                            .width(30.dp)
                            .height(30.dp)
                            .align(Alignment.Bottom)
                    )
                    Text(
                        text = insulin.name,
                        color = DiaryTheme.colors.text,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
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
