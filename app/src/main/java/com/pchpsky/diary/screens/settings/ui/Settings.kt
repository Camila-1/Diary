package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.InsulinEntry
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.interfaces.CurrentSettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun Settings(
    navController: NavHostController,
    viewModel: CurrentSettingsViewModel
) {

    Column (
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background),
    ) {
        GlucoseCard()
        InsulinCard(listOf(Insulin("", "#ff4483", "Example"))) {
            navController.navigate(MainRout.INSULIN_SETTINGS.route)
        }
    }
}

@Composable
fun CategoryCard(content: @Composable () -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        backgroundColor = DiaryTheme.colors.cardBackground,
        shape = DiaryTheme.shapes.roundedCard,
        content = content
    )
}

@Composable
fun GlucoseCard() {

    val rememberedObserver = remember { mutableStateOf("") }

    val items = listOf("mg/dL", "mmol/gL")

    CategoryCard {
        LazyColumn {
            item {
                Text(
                    text = "Glucose",
                    style = DiaryTheme.typography.primaryHeader,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp, start = 30.dp)
                )
            }

            items(items) { unit->
                Row(
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent, DiaryTheme.shapes.roundedButton)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            rememberedObserver.value = unit
                        }
                ) {
                    RadioButton(
                        selected = rememberedObserver.value == unit,
                        onClick = { rememberedObserver.value = unit },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = DiaryTheme.colors.primary,
                            unselectedColor = Color.White
                        ),
                    )

                    Text(
                        text = unit,
                        modifier = Modifier.padding(start = 25.dp),
                        style = DiaryTheme.typography.body,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun InsulinCard(insulins: List<Insulin>, onEditClick: () -> Unit) {

    CategoryCard {
        LazyColumn {
            item {
                Text(
                    text = "Insulin",
                    style = DiaryTheme.typography.primaryHeader,
                    color = Color.White,
                )
            }

            items(insulins) { insulin ->
                InsulinEntry(insulin)
            }
        }
    }
}

@Preview
@Composable
fun SettingsPreview() {
    DiaryTheme {
        Settings(rememberNavController(), FakeSettingsViewModel)
    }
}

