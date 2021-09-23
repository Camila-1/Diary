package com.pchpsky.diary.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(10),
    medium = RoundedCornerShape(35),
    large =  CutCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    ),
)