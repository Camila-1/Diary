package com.pchpsky.diary.utils.extensions

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toHex(): String {
    return "#" + Integer.toHexString(toArgb()).drop(2)
}

fun Color.fromHex(hexColor: String): Color {
    return Color(parseColor("#$hexColor"))
}