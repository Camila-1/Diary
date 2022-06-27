package com.pchpsky.diary.presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.presentation.theme.DiaryTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DateView(time: String, onClick: () -> Unit) {

    AnimatedContent(
        targetState = time,
        transitionSpec = { ContentTransform(fadeIn(), fadeOut()) }
    ) {
        Text(
            text = time,
            modifier = Modifier
                .clickable { onClick() },
            style = DiaryTheme.typography.insulinUnits
        )
    }
}

@Composable
@Preview
fun TimeViewPreview() {
    DiaryTheme {
        DateView(
            time = "april 15",
            onClick = {}
        )
    }
}