package com.pchpsky.diary.screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.R
import com.pchpsky.diary.theme.green
import com.pchpsky.diary.theme.lightGreen

@Composable
fun login(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        logoGroup()
        loginButton(
            text = "Login",
            color = lightGreen,
            onClick = { navController.navigate("login") })
        loginButton(text = "Signup", color = green, onClick = { navController.navigate("signup") })
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

@Composable
fun logoGroup() {
    val image = painterResource(R.drawable.ic_logo)
    Row {
        Image(painter = image, contentDescription = null)
        Text(text = "Diary")
    }
}

@Preview
@Composable
fun defaultPreview() {
    login(rememberNavController())
}
