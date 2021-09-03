package com.pchpsky.diary.screens.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DiaryTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography,
        shapes = shapes
    )
}

object DiaryTheme {
    val colors: Colors
    @Composable
    get() = MaterialTheme.colors

    val typography
    @Composable
    get() = MaterialTheme.typography

    val shapes
    @Composable
    get() = MaterialTheme.shapes
}