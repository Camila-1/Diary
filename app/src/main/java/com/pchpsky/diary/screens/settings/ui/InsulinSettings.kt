package com.pchpsky.diary.screens.settings.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.*
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.extensions.toHex
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.SettingsState
import com.pchpsky.diary.screens.settings.interfaces.InsulinViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun InsulinSettings(
    viewModel: InsulinViewModel,
    navController: NavController
) {

    val insulinName = remember { mutableStateOf("") }
    val insulinColor = remember { mutableStateOf(Color(Color.Yellow.toArgb())) }
    val scope = rememberCoroutineScope()
    val uiState: SettingsState by viewModel.uiState.collectAsState()
    val insulins by viewModel.insulins.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    scope.launch {
        viewModel.insulins()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
    ) {

        AddInsulin(insulinName, insulinColor) {
            keyboardController?.hide()
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

        InsulinColorCircle(color.value, modifier = Modifier.align(Alignment.Bottom))

        LinedTextField(
            value = name,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(10f)
        )

        RoundedOutlinedButton(
            modifier = Modifier
                .height(35.dp)
                .align(Alignment.Bottom)
                .weight(3f),
            onClick = onAddClick
        )
    }
}

@Composable
fun InsulinList(insulins: List<Insulin>) {

    val listState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxWidth(),
        state = listState
    ) {

        item {
            Text(
                text = "Insulins",
                color = Color.White,
                style = DiaryTheme.typography.primaryHeader
            )
        }

        items(insulins) { insulin ->
            InsulinEntry(insulin)
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun InsulinSettingsPreview() {
    DiaryTheme {
        InsulinSettings(FakeSettingsViewModel, rememberNavController())
    }
}
