package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pchpsky.diary.theme.DiaryColors
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun Settings() {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background)
    ) {
        GlucoseGroup()
    }
}

@Composable
fun GlucoseGroup() {

    val rememberedObserver = remember { mutableStateOf("") }

    val items = listOf("mg/dL", "mmol/gL")

    Surface(color = Color.White.copy(0.1f), modifier = Modifier.wrapContentHeight().wrapContentWidth(), shape = DiaryTheme.shapes.small) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, DiaryTheme.shapes.small)
                .wrapContentHeight()
                .padding(10.dp),
            backgroundColor = Color.Gray
        ) {
            Column {
                Text(
                    text = "Glucose",
                    style = DiaryTheme.typography.h2,
                    color = Color.White,
                    modifier = Modifier.padding(top = 30.dp, start = 30.dp)
                )

                items.forEach { item ->
                    Column {
                        Row(
                            modifier = Modifier.padding(30.dp),

                            ) {
                            RadioButton(
                                selected = rememberedObserver.value == item,
                                onClick = { rememberedObserver.value = item },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = DiaryTheme.colors.primary,
                                    unselectedColor = Color.White
                                ),
                            )

                            Text(
                                text = item,
                                modifier = Modifier.padding(start = 25.dp),
                                style = DiaryTheme.typography.body1,
                                color = Color.White,
                            )
                        }

                    }
                }
            }
        }

    }



}

@Preview
@Composable
fun SettingsPreview() {
    GlucoseGroup()
}

