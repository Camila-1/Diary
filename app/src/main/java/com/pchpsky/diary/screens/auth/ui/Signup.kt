package com.pchpsky.diary.screens.auth.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.composables.TextField
import com.pchpsky.diary.screens.auth.AuthActivity
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.auth.FakeViewModel
import com.pchpsky.diary.screens.theme.blue
import com.pchpsky.diary.screens.theme.green

data class AuthError(var isError: Boolean = true, var errorMessage: String? = null)

@Composable
fun SignUp(viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }
    val uiState: AuthState by viewModel.uiState.collectAsState()

    @Composable
    fun errorFor(key: String): MutableState<AuthError> {
        return remember { mutableStateOf(
            when(uiState) {
                is AuthState.ValidationError -> {
                    AuthError(errorMessage = (uiState as AuthState.ValidationError).fields[key]?.get(0))
                }
                is AuthState.PasswordDoesNotConfirmed -> {
                    AuthError(errorMessage = "Does not mach password")
                }
                else -> AuthError(false, null)
            }
        ) }
    }

//    fun errorFor(key: String): AuthError {
//        return if(uiState is AuthState.ValidationError) {
//            AuthError(errorMessage = (uiState as AuthState.ValidationError).fields[key]?.get(0))
//        } else AuthError(false, null)
//    }

    if (uiState is AuthState.SignupSuccessful) {
        val context = LocalContext.current
        openHomeScreen(context)
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EmailInputField(email, errorFor("email"))
            PasswordInputField(password, errorFor("password"))
            ConfirmPasswordInputField(passwordConfirmation, errorFor(""))
            SubmitButton(
                "Submit",
                onClick = {
                    viewModel.createUser(email.value, password.value, passwordConfirmation.value)
                },
                color = green
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun EmailInputField(value: MutableState<String>, error: MutableState<AuthError>) {

    TextField(value, "Email", error)
    if (error.value.isError) ErrorMessage(error)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PasswordInputField(value: MutableState<String>, error: MutableState<AuthError>) {

    TextField(value, "Password", error)
    if (error.value.isError) ErrorMessage(error)
}

@Composable
fun ConfirmPasswordInputField(value: MutableState<String>, error: MutableState<AuthError>) {

    TextField(value, "Confirm Password", error)
    if (error.value.isError) ErrorMessage(error)
}

@Composable
fun SubmitButton(text: String, color: Color, onClick: () -> Unit) {
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
fun ErrorMessage(error: MutableState<AuthError>) {
    Text(
        text = error.value.errorMessage ?: "",
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp).background(Color.White)
    )
}

fun openHomeScreen(context: Context) {
    context.startActivity(Intent(context, MainActivity::class.java))
    (context as AuthActivity).finish()
}

@Preview
@Composable
fun SignUpPreview() {
    SignUp(FakeViewModel)
}