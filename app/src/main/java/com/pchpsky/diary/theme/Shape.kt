package com.pchpsky.diary.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



val shapes = DiaryShapes(
    roundedButton = RoundedCornerShape(50),
    roundedCard = RoundedCornerShape(8),
    roundedTextField = RoundedCornerShape(8),
    navigationDrawer = CutCornerShape(0.dp)
)

data class DiaryShapes(
    val roundedButton: Shape,
    val roundedCard: Shape,
    val roundedTextField: Shape,
    val navigationDrawer: Shape
    )



val LocalDiaryShapes = staticCompositionLocalOf<DiaryShapes> {
    error("No Diary Shape Provided")
}