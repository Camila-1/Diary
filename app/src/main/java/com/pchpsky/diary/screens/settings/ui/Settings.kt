package com.pchpsky.diary.screens.settings.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
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
                .background(DiaryTheme.colors.background)
                .padding(30.dp),
        ) {

            item {
                Glucose(glucoseUnit) {
                    scope.launch {
                        viewModel.updateGlucoseUnit(it)
                    }
                }
            }

            item {
                Divider(
                    color = DiaryTheme.colors.divider,
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
        CategoryHeader(modifier = Modifier.padding(bottom = 20.dp),"Glucose")

        GlucoseUnits.units.forEach {
            Row(
                modifier = Modifier
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
                    modifier = Modifier.size(30.dp)
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
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryHeader(modifier = Modifier, "Insulin")

            Icon(
                imageVector = Icons.Rounded.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onEditClick()
                    }
                    .background(Color.Blue),
                tint = Color.White,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            insulins.forEach { insulin ->
                InsulinEntry(insulin)
            }
        }


    }
}

@Composable
fun CategoryHeader(modifier: Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        style = DiaryTheme.typography.primaryHeader,
        color = DiaryTheme.colors.text,
    )
}

@Preview
@Composable
fun SettingsPreview() {
    DiaryTheme {
        Settings(rememberNavController(), FakeSettingsViewModel)
    }
}

