package com.pchpsky.diary.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import android.graphics.Color.parseColor

fun Color.toHex(): String {
    return "#" + Integer.toHexString(toArgb()).drop(2)
}

fun Color.fromHex(hexColor: String): Color {
    return Color(parseColor("#$hexColor"))
}