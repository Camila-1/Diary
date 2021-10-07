package com.pchpsky.diary.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val dimens = Dimens(
    cornerCardSmallHeight = 20.dp,
    cornerCardLargeHeight = 200.dp
)

data class Dimens(
    val cornerCardSmallHeight: Dp,
    val cornerCardLargeHeight: Dp
)

val LocalDiaryDimens = staticCompositionLocalOf<Dimens> {
    error("No Diary Dimens Provided")
}