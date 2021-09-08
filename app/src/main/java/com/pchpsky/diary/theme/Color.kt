package com.pchpsky.diary.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val lightGreen = Color(0xff5D9A0F)
val green = Color(0xff29771C)
val blue = Color(0xff60A5FF)
val backgroundDark = Color(0xff0D1A26)

val DarkColors = darkColors(
    background = backgroundDark,
    error = Color.Red,
    primary = green,
    secondary = lightGreen,

)

val LightColors = lightColors()

class DiaryColors(
    greenButton: Color,
    lightGreenButton: Color,
    background: Color,

)




