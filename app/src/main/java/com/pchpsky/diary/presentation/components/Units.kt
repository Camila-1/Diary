package com.pchpsky.diary.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.components.textfield.UnitsInputField
import com.pchpsky.diary.presentation.theme.DiaryTheme

@SuppressLint("UnrememberedMutableState")
@ExperimentalComposeUiApi
@Composable
fun Units(
    units: String,
    modifier: Modifier,
    increment: () -> Unit,
    decrement: () -> Unit,
    setUnits: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .wrapContentWidth()
    ) {

        UnitsInputField(
            units = mutableStateOf(units),
            setUnits = { points -> setUnits(points) }
        )

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            IconButton(onClick = { increment() }) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }

            IconButton(onClick = { decrement() }) {
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
    DiaryTheme {
        Units(
            units = "1",
            modifier = Modifier,
            increment = {},
            decrement = {},
            setUnits = {}
        )
    }
}