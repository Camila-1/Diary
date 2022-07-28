package com.pchpsky.diary.presentation.launch

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.presentation.auth.AuthActivity
import com.pchpsky.diary.presentation.theme.blue

@Composable
fun LaunchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
       val context = LocalContext.current

        Button(
            onClick = { openHome(context) },
            modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = blue
            )
        ) { Text(text = "Home") }

        Button(
            onClick = { openAuth(context) },
            modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally).padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = blue
            )
        ) { Text(text = "Auth") }
    }
}

fun openHome(context: Context) {
    context.startActivity(Intent(context, MainActivity::class.java))
    (context as LaunchActivity).finish()
}

fun openAuth(context: Context) {
    context.startActivity(Intent(context, AuthActivity::class.java))
    (context as LaunchActivity).finish()
}