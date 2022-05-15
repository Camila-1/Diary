package com.pchpsky.diary.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.components.textfield.UnitsInputField
import com.pchpsky.diary.presentation.theme.DiaryTheme

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedMutableState")
@ExperimentalComposeUiApi
@Composable
fun UnitsCounter(
    units: Double,
    modifier: Modifier,
    increment: () -> Unit,
    decrement: () -> Unit,
    setUnits: (String) -> Unit
) {
    val previousValue = remember { mutableStateOf(units) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .wrapContentWidth()
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, DiaryTheme.colors.divider, DiaryTheme.shapes.roundedTextField)
                .wrapContentSize()
        ) {
            AnimatedContent(
                targetState = units,
                transitionSpec = {
                    if (targetState < previousValue.value) {
                        slideInVertically { fullHeight -> fullHeight } + fadeIn() with
                                slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
                    } else {
                        slideInVertically { fullHeight -> -fullHeight } + fadeIn() with
                                slideOutVertically { fullHeight -> fullHeight } + fadeOut()
                    }.using(SizeTransform(clip = false))
                }
            ) {
                Text(
                    text = units.toString(),
                    style = DiaryTheme.typography.insulinUnits,
                    modifier = Modifier
                        .width(160.dp)
                )
            }
        }

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            IconButton(
                onClick = {
                    previousValue.value = units
                    increment()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }

            IconButton(
                onClick = {
                    previousValue.value = units
                    decrement()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.RemoveCircle,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun UnitsPreview() {
    var units by remember { mutableStateOf(1.0) }
    DiaryTheme {
        UnitsCounter(
            units = units,
            modifier = Modifier,
            increment = { units++ },
            decrement = { units-- },
            setUnits = {}
        )
    }
}