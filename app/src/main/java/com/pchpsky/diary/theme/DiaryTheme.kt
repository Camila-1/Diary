package com.pchpsky.diary.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DiaryTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.background.copy(alpha = .5f)
        )
    }

    ProvideDiaryColors(colors) {
        MaterialTheme(
            content = content,
            typography = typography,
            shapes = shapes
        )
    }

}

object DiaryTheme {
    val colors: DiaryColors
    @Composable
    get() = LocalDiaryColors.current

    val typography
    @Composable
    get() = MaterialTheme.typography

    val shapes
    @Composable
    get() = MaterialTheme.shapes
}