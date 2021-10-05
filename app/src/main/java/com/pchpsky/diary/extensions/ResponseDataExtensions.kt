package com.pchpsky.diary.extensions

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color

object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(parseColor("#$colorString"))
    }
}