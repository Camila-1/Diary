package com.pchpsky.diary.screens.auth.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.composables.TextField
import com.pchpsky.diary.screens.auth.AuthActivity
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.auth.FakeViewModel
import com.pchpsky.diary.screens.theme.green


@Composable
fun SignUp(viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }
    val uiState: AuthState by viewModel.uiState.collectAsState()

    fun errorMessageFor(key: String): String? {
        return if (uiState is AuthState.ValidationError)
            (uiState as AuthState.ValidationError).fields[key]?.get(0)
        else null
    }

    if (uiState is AuthState.SignupSuccessful) {
        val context = LocalContext.current
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as AuthActivity).finish()
    }

    @Composable
    fun errorMessage(message: String?) {
        Text(
            text = message ?: "",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val emailErrorMessage = errorMessageFor("email")
            val passwordErrorMessage = errorMessageFor("password")
            val confirmationErrorMessage = errorMessageFor("confirm password")



            TextField(email, "Email", emailErrorMessage)
            if (emailErrorMessage != null) {
                errorMessage(emailErrorMessage)
            }

            TextField(password, "Password", passwordErrorMessage)
            if (passwordErrorMessage != null) {
                errorMessage(passwordErrorMessage)
            }

            TextField(passwordConfirmation, "Confirm Password", confirmationErrorMessage)
            if (confirmationErrorMessage != null) {
                errorMessage(confirmationErrorMessage)
            }

            Button(
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = green,
                    disabledBackgroundColor = green
                ),
                onClick = {
                    viewModel.createUser(
                        email.value,
                        password.value,
                        passwordConfirmation.value
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Submit")
            }
        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    SignUp(FakeViewModel)
}