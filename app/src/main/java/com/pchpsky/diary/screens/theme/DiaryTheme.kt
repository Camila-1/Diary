package com.pchpsky.diary.screens.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DiaryTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = DarkColors

    MaterialTheme(
        colors = colors,
        content = content
    )
}

object DiaryTheme {
    val colors: Colors
    @Composable
    get() = MaterialTheme.colors
}