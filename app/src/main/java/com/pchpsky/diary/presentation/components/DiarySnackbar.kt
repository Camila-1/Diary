package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue

@Composable
fun DiarySnackbar(snackbarData: SnackbarData, color: Color, modifier: Modifier) {

    Box(
        modifier = modifier
            .background(color, DiaryTheme.shapes.snackbar)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Text(
            text = snackbarData.message,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = DiaryTheme.typography.snackbar,
            color = Color.White

        )
    }
}

@Composable
@Preview
fun SnackbarPreview() {
    DiaryTheme {
        DiarySnackbar(
            snackbarData = object : SnackbarData {
                override val actionLabel: String = ""
                override val duration: SnackbarDuration = SnackbarDuration.Long
                override val message: String = "Snackbar"
                override fun dismiss() {}
                override fun performAction() {}
            },
            color = blue,
            modifier = Modifier.padding(20.dp)
        )
    }
}