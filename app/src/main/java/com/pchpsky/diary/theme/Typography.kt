package com.pchpsky.diary.theme


import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = Typography(
    h1 = TextStyle(
        color = Color.White,
        fontSize = 50.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif,
    ),
    h2 = TextStyle(
        color = Color.White,
        fontSize = 40.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif,
    )
)