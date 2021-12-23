package com.pchpsky.diary.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
            .semantics { contentDescription = "Home screen" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButton("insulin", Color.Gray) {}
        LoginButton("glucose", Color.Gray) {}
        LoginButton("water", Color.Gray) {}
        LoginButton("eating", Color.Gray) {}
        LoginButton("sport", Color.Gray) {}
    }
}

@Composable
fun LoginButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = color
        ),
        onClick = { onClick.invoke() },
        modifier = Modifier
            .width(250.dp)
            .padding(12.dp)
            .height(40.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text(text)
    }
}

@Composable
@Preview
fun HomePreview() {
    DiaryTheme {
        Home()
    }
}