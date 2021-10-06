package com.pchpsky.diary.screens.auth.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.composables.LogoGroup
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.green
import com.pchpsky.diary.theme.lightGreen

@Composable
fun StartScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.width(250.dp)
        ) {
            LogoGroup(modifier = Modifier.padding(bottom = 40.dp))
            LoginButton(
                text = "Login",
                color = lightGreen,
                onClick = { navController.navigate(AuthRoute.LOGIN.route) })
            LoginButton(
                text = "Signup",
                color = green,
                onClick = { navController.navigate(AuthRoute.SIGNUP.route) })
        }
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
        onClick = {
            onClick.invoke()
                  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(40.dp),
        shape = DiaryTheme.shapes.roundedButton
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    DiaryTheme {
        StartScreen(rememberNavController())
    }
}
