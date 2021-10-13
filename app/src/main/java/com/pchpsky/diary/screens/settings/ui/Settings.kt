package com.pchpsky.diary.screens.settings.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.InsulinEntry
import com.pchpsky.diary.components.ProgressBar
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.GlucoseUnits
import com.pchpsky.diary.screens.settings.SettingsState
import com.pchpsky.diary.screens.settings.interfaces.SettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Settings(
    navController: NavHostController,
    viewModel: SettingsViewModel
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    val glucoseUnit = remember { mutableStateOf("") }
    val insulins = remember { mutableStateOf(emptyList<Insulin>()) }

    @Composable
    fun Screen() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(DiaryTheme.colors.background),
        ) {

            item {
                Glucose(glucoseUnit) {
                    scope.launch {
                        viewModel.updateGlucoseUnit(it)
                    }
                }
            }
            item {
                Button(
                    modifier = Modifier.padding(start = 30.dp),
                    onClick = { navController.navigate(MainRout.INSULIN_SETTINGS.route) },
                    shape = DiaryTheme.shapes.roundedButton,
                    content = {
                        Text("Insulin Settings")
                    }
                )
            }
            item {
                Divider(
                    color = DiaryTheme.colors.divider,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    thickness = 1.dp
                )
            }
            item {
                Insulin(insulins.value) {
                    navController.navigate(MainRout.INSULIN_SETTINGS.route)
                }
            }
        }
    }

    when(uiState) {
        is SettingsState.None -> scope.launch {
            viewModel.settings()
        }
        is SettingsState.Loading -> ProgressBar(true)
        is SettingsState.Settings -> {
            glucoseUnit.value = (uiState as SettingsState.Settings).glucoseInit
            insulins.value = (uiState as SettingsState.Settings).insulins
            ProgressBar(false)
            Screen()
        }
    }
}

@Composable
fun Glucose(unit: MutableState<String>, onClick: (GlucoseUnits) -> Unit) {

    Column {
        CategoryHeader("Glucose")

        GlucoseUnits.units.forEach {
            Row(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent, DiaryTheme.shapes.roundedButton)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        unit.value = it.unit
                        onClick(it)
                    }
            ) {
                RadioButton(
                    selected = unit.value == it.unit,
                    onClick = {
                        unit.value = it.unit
                        onClick(it)
                        },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = DiaryTheme.colors.primary,
                        unselectedColor = Color.White
                    ),
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp).size(30.dp)
                )

                Text(
                    text = it.unit,
                    modifier = Modifier.padding(start = 25.dp).align(Alignment.CenterVertically),
                    style = DiaryTheme.typography.body,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun Insulin(insulins: List<Insulin>, onEditClick: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(bottom = 15.dp, start = 20.dp, end = 20.dp)
    ) {
        CategoryHeader("Insulin")

        insulins.forEach { insulin ->
            InsulinEntry(insulin)
        }
    }
}

@Composable
fun CategoryHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(start = 30.dp, top = 20.dp, bottom = 10.dp),
        style = DiaryTheme.typography.primaryHeader,
        color = DiaryTheme.colors.text
    )
}

@Preview
@Composable
fun SettingsPreview() {
    DiaryTheme {
        Settings(rememberNavController(), FakeSettingsViewModel)
    }
}

