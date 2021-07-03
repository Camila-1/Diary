package com.pchpsky.diary.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun home() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        loginButton("insulin", Color.Blue, {})
        loginButton("glucose", Color.Blue, {})
        loginButton("water", Color.Blue, {})
        loginButton("eating", Color.Blue, {})
        loginButton("sport", Color.Blue, {})
    }
}

@Composable
fun loginButton(text: String, color: Color, onClick: () -> Unit) {
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