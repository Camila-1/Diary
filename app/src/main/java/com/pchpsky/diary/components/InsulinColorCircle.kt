package com.pchpsky.diary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun InsulinColorCircle(color: Color, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(color, CircleShape)
            .width(30.dp)
            .height(30.dp)
    )
}

