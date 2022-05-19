package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun UnitsCounter(
    units: Double,
    onChange: (Double) -> Unit,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .wrapContentWidth()
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, DiaryTheme.colors.divider, DiaryTheme.shapes.roundedTextField)
                .width(193.dp),
        ) {
            TextField(
                value = units.toString(),
                onValueChange = { it.toDouble().apply(onChange) },
                textStyle = DiaryTheme.typography.insulinUnits,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )
        }

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            IconButton(
                onClick = {
                    onChange(units + 1.0)
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
                    onChange(units - 1.0)
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
    var units by remember { mutableStateOf(12.0) }
    DiaryTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            UnitsCounter(
                units = units,
                modifier = Modifier
                    .align(Alignment.Center),
                onChange = { units = it }
            )
        }
    }
}