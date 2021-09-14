package com.pchpsky.diary.theme

import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val lightGreen = Color(0xff5D9A0F)
val green = Color(0xff29771C)
val blue = Color(0xff60A5FF)
val backgroundDark = Color(0xff060b15)

val DarkColors = DiaryColors(
    background = backgroundDark,
    error = Color.Red,
    primary = green,
    secondary = lightGreen
)

val LightColors = DiaryColors(
    background = backgroundDark,
    error = Color.Red,
    primary = green,
    secondary = lightGreen
)

@Stable
class DiaryColors(
    background: Color,
    primary: Color,
    error: Color,
    secondary: Color
) {
    var background by mutableStateOf(background)
    private set

    var primary by mutableStateOf(primary)
    private set

    var error by mutableStateOf(error)

    var secondary by mutableStateOf(secondary)

    fun copy(): DiaryColors = DiaryColors(
        background = backgroundDark,
        error = Color.Red,
        primary = green,
        secondary = lightGreen
    )

    fun update(other: DiaryColors) {
        background = other.background
        error = other.error
        primary = other.primary
        secondary = other.secondary
    }
}

@Composable
fun ProvideDiaryColors(
    diaryColors: DiaryColors,
    content: @Composable () -> Unit
) {
    val colors = remember{ diaryColors.copy() }
    colors.update(diaryColors)
    CompositionLocalProvider(LocalDiaryColors provides colors, content = content)
}

val LocalDiaryColors = staticCompositionLocalOf<DiaryColors> {
    error("No DiaryColors provided")
}




