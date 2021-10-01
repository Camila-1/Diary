package com.pchpsky.diary.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val lightGreen = Color(0xff5D9A0F)
val green = Color(0xff29771C)
val blue = Color(0xff60A5FF)
val backgroundDark = Color(0xff060b15)
val backgroundDrawer = Color(0xff1b1b1b)
val lightGrey = Color(0xffa1a1aa)
val errorRed = Color(0xffb71c1c)

val DarkColors = DiaryColors(
    background = backgroundDark,
    error = errorRed,
    primary = green,
    secondary = lightGreen,
    backgroundDrawer = backgroundDrawer,
    text = Color.White,
    inputField = lightGrey,
    focusedInputField = blue
)

val LightColors = DiaryColors(
    background = backgroundDark,
    error = errorRed,
    primary = green,
    secondary = lightGreen,
    backgroundDrawer = backgroundDrawer,
    text = Color.Black,
    inputField = lightGrey,
    focusedInputField = blue
)

@Stable
class DiaryColors(
    background: Color,
    primary: Color,
    error: Color,
    secondary: Color,
    backgroundDrawer: Color,
    text: Color,
    inputField: Color,
    focusedInputField: Color
) {
    var background by mutableStateOf(background)
        private set

    var primary by mutableStateOf(primary)
        private set

    var error by mutableStateOf(error)
        private set

    var secondary by mutableStateOf(secondary)
        private set

    var backgroundDrawer by mutableStateOf(backgroundDrawer)
        private set

    var text by mutableStateOf(com.pchpsky.diary.theme.lightGrey)
    private set

    var grey by mutableStateOf(lightGrey)
    private set

    var focusedInputFieldBorder by mutableStateOf(blue)

    fun copy(): DiaryColors = DiaryColors(
        background = backgroundDark,
        error = Color.Red,
        primary = green,
        secondary = lightGreen,
        backgroundDrawer = backgroundDrawer,
        text = com.pchpsky.diary.theme.lightGrey,
        inputField = grey,
        focusedInputField = blue
    )

    fun update(other: DiaryColors) {
        background = other.background
        error = other.error
        primary = other.primary
        secondary = other.secondary
        backgroundDrawer = other.backgroundDrawer
        text = other.text
        grey = other.grey
        focusedInputFieldBorder = other.focusedInputFieldBorder
    }
}

@Composable
fun ProvideDiaryColors(
    diaryColors: DiaryColors,
    content: @Composable () -> Unit
) {
    val colors = remember{ diaryColors }
    colors.update(diaryColors)
    CompositionLocalProvider(LocalDiaryColors provides colors, content = content)
}

val LocalDiaryColors = staticCompositionLocalOf<DiaryColors> {
    error("No DiaryColors provided")
}




