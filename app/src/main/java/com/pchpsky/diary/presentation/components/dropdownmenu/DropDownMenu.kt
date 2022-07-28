package com.pchpsky.diary.presentation.components.dropdownmenu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.data.entities.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InsulinDropDownMenu(
    modifier: Modifier,
    expanded: Boolean,
    items: List<Insulin>,
    onItemSelected: (Insulin) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        modifier = modifier
            .background(DiaryTheme.colors.background),
        expanded = expanded,
        onDismissRequest = onDismiss,

    ) {
        AnimatedContent(
            targetState = expanded
        ) {
            Column(
                modifier = Modifier
            ) {
                items.forEach {
                    DropDownMenuItem(insulin = it, onClick = { onItemSelected(it) })
                }
            }
        }

    }
}

@Composable
@Preview
fun DropDownMenuPreview() {
    val items = listOf(
        Insulin("id", Color.Blue.toHex(), "Test Insulin"),
        Insulin("id", Color.Red.toHex(), "Test Insulin test test test"),
        Insulin("id", Color.Green.toHex(), "Test Insulin 3"),
    )

    val expanded = remember {
        mutableStateOf(false)
    }

    DiaryTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.Center),
                onClick = { expanded.value = !expanded.value }) {
                Text(text = "Open")
            }

            InsulinDropDownMenu(
                modifier = Modifier.background(DiaryTheme.colors.background),
                items = items,
                expanded = expanded.value,
                onItemSelected = { expanded.value = false },
                onDismiss = {}
            )
        }
    }
}