package com.pchpsky.diary.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun RoundedOutlinedButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(35.dp),
        border = BorderStroke(1.dp, DiaryTheme.colors.unfocusedInputFieldBorder),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        ),
        shape = DiaryTheme.shapes.roundedButton

    ) {
        Text(
            text = "Add",
            fontSize = 12.sp
        )
    }
}