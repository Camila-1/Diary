package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun Settings() {

    Column (
        modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background)
    ) {

        GlucoseGroup()
        InsulinGroup()
    }
}

@Composable
fun GlucoseGroup() {

    val rememberedObserver = remember { mutableStateOf("") }

    val items = listOf("mg/dL", "mmol/gL")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        backgroundColor = Color(0xff484848).copy(0.8f),
    ) {
        Column {
            Text(
                text = "Glucose",
                style = DiaryTheme.typography.h2,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp, start = 30.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
            ) {
                items.forEach { item ->
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent, DiaryTheme.shapes.small)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                            rememberedObserver.value = item
                        },
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

@Composable
fun InsulinGroup() {

    val insulins = mapOf<Color, String>(
        Color.Blue to "Insulin 1",
        Color.Red to "Insulin 2",
        Color.Yellow to "Insulin 3"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        backgroundColor = Color(0xff484848).copy(0.8f),
    ) {
        Column {

            Text(
                text = "Insulin",
                style = DiaryTheme.typography.h2,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp, start = 30.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
            ) {
                insulins.forEach { (color, name) ->

                    Row(
                        modifier = Modifier.padding(start = 30.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color, CircleShape)
                                .width(15.dp)
                                .height(15.dp)
                        )
                        Text(
                            text = name,
                            modifier = Modifier.padding(start = 25.dp),
                            style = DiaryTheme.typography.body1,
                            color = Color.White
                        )
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

