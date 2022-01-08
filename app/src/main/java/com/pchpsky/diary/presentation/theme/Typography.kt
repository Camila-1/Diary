package com.pchpsky.diary.presentation.theme


import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val typography = DiaryTypography(
    logo = TextStyle(
        fontSize = 60.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif
    ),
    authScreenHeader = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif
    ),
    primaryHeader = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif,
    ),
    body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    textField = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        fontFamily = FontFamily.SansSerif,
        color = Color.White
    ),
    basicTextField = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W300,
        fontFamily = FontFamily.SansSerif
    ),
    insulinUnits = TextStyle(
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        color = Color.White
    ),
    snackbar = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
    )
)

data class DiaryTypography(
    val logo: TextStyle,
    val authScreenHeader: TextStyle,
    val primaryHeader: TextStyle,
    val body: TextStyle,
    val textField: TextStyle,
    val basicTextField: TextStyle,
    val insulinUnits: TextStyle,
    val snackbar: TextStyle
)

val LocalDiaryTypography = staticCompositionLocalOf<DiaryTypography> {
    error("No Diary Typography Provided")
}