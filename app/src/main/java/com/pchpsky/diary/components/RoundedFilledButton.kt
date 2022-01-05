package com.pchpsky.diary.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun RoundedFilledButton(text: String, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Button(
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = color
        ),
        onClick = onClick,
        modifier = modifier
            .padding(bottom = 12.dp)
            .height(40.dp),
        shape = DiaryTheme.shapes.roundedButton

    ) {
        Text(text)
    }
}