package com.pchpsky.diary.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val lightGreen = Color(0xff5D9A0F)
val green = Color(0xff29771C)
val blue = Color(0xff60A5FF)
val backgroundDark = Color(0xff060b15)
val backgroundDrawer = Color(0xff1b1b1b)

val DarkColors = DiaryColors(
    background = backgroundDark,
    error = Color.Red,
    primary = green,
    secondary = lightGreen,
    backgroundDrawer = backgroundDrawer
)

val LightColors = DiaryColors(
    background = backgroundDark,
    error = Color.Red,
    primary = green,
    secondary = lightGreen,
    backgroundDrawer = backgroundDrawer
)

@Stable
class DiaryColors(
    background: Color,
    primary: Color,
    error: Color,
    secondary: Color,
    backgroundDrawer: Color
) {
    var background by mutableStateOf(background)
    private set

    var primary by mutableStateOf(primary)
    private set

    var error by mutableStateOf(error)

    var secondary by mutableStateOf(secondary)

    var backgroundDrawer by mutableStateOf(backgroundDrawer)

    fun copy(): DiaryColors = DiaryColors(
        background = backgroundDark,
        error = Color.Red,
        primary = green,
        secondary = lightGreen,
        backgroundDrawer = backgroundDrawer
    )

    fun update(other: DiaryColors) {
        background = other.background
        error = other.error
        primary = other.primary
        secondary = other.secondary
        backgroundDrawer = other.backgroundDrawer
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




