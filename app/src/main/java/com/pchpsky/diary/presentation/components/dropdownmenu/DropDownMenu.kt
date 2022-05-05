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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InsulinDropDownMenu(
    items: List<Insulin>,
    isOpen: MutableState<Boolean>,
    onItemSelected: (Insulin) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        modifier = Modifier
            .background(DiaryTheme.colors.background),
        expanded = isOpen.value,
        onDismissRequest = onDismiss,
        offset = DpOffset(0.dp, 8.dp),

    ) {
        AnimatedContent(
            targetState = isOpen
        ) {
            Column(
                modifier = Modifier
                    .background(DiaryTheme.colors.background)
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

    val isOpen = remember {
        mutableStateOf(false)
    }

    DiaryTheme {
        Button(onClick = { isOpen.value = !isOpen.value }) {
            Text(text = "Open")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            InsulinDropDownMenu(
                items = items,
                isOpen = isOpen,
                onItemSelected = { isOpen.value = false },
                onDismiss = {}
            )
        }
    }
}