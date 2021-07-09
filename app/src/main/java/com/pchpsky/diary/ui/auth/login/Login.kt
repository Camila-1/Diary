package com.pchpsky.diary.ui.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.ui.theme.blue
import com.pchpsky.diary.ui.theme.green
import com.pchpsky.diary.ui.theme.lightGreen

@Composable
fun Login() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Sign In", color = Color.White, fontSize = 40.sp)
            EmailInputField()
            PasswordInputField()
            LoginButton("Login", onClick = {}, color = lightGreen)
        }
    }
}

@Composable
fun EmailInputField() {
    var email by remember { mutableStateOf("") }


    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp),
        textStyle = TextStyle(color = Color.White),
        label = { Text(text = "Email", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        )
    )
}

@Composable
fun PasswordInputField() {
    var email by remember { mutableStateOf("") }


    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp),
        textStyle = TextStyle(color = Color.White),
        label = { Text(text = "Password", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        )
    )
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
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(40.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text(text)
    }
}

@Composable
@Preview
fun defaultPreview() {
    Login()
}
