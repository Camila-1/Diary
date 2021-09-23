package com.pchpsky.diary.screens.settings.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun Settings() {
    Text(
        text = "SETTINGS",
        style = DiaryTheme.typography.h2,
        modifier = Modifier.padding(50.dp)
    )
}