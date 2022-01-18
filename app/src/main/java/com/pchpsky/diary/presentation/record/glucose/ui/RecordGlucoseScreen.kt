package com.pchpsky.diary.presentation.record.glucose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pchpsky.diary.presentation.components.RecordGlucoseTopBar
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun RecordGlucoseScreen(onBackClick: () -> Unit) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { RecordGlucoseTopBar { onBackClick() } }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background)
        )
    }
}

@Composable
@Preview
fun RecordGlucosePreview() {
    DiaryTheme {
        RecordGlucoseScreen {}
    }
}