package com.pchpsky.diary.screens.auth.ui

import android.annotation.SuppressLint
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
import com.pchpsky.diary.screens.auth.AuthActivity
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.theme.blue
import com.pchpsky.diary.screens.theme.green
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AuthError(var isError: Boolean = true, val errorMessage: String?)

@Composable
fun SignUp(viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirmation = remember { mutableStateOf("") }

    val uiState: AuthState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    fun errorFor(key: String): AuthError {
        return if(uiState is AuthState.ValidationError) {
             AuthError(errorMessage = (uiState as AuthState.ValidationError).fields[key]?.get(0))
        } else AuthError(false, null)
    }

    if (uiState is AuthState.SignupSuccessful) {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as AuthActivity).finish()
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
            ConfirmPasswordInputField(passwordConfirmation, AuthError(
                isError = uiState is AuthState.PasswordDoesNotConfirmed,
                errorMessage = "Does not mach password"
            ))
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
fun EmailInputField(value: MutableState<String>, error: AuthError) {

    TextField(value, "Email", error)

    if (error.isError and (error.errorMessage != null)) {
        ErrorMessage(error.errorMessage)
    }
}

@Composable
fun PasswordInputField(value: MutableState<String>, error: AuthError) {

    TextField(value, "Password", error)

    if (error.isError and (error.errorMessage != null)) {
        ErrorMessage(error.errorMessage)
    }
}

@Composable
fun ConfirmPasswordInputField(value: MutableState<String>, error: AuthError) {

    TextField(value, "Confirm Password", error)

    if (error.isError and (error.errorMessage != null)) {
        ErrorMessage(error.errorMessage)
    }
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
fun TextField(value: MutableState<String>, label: String, error: AuthError) {

    Column {
        OutlinedTextField(
            value = value.value,
            onValueChange = {
                error.isError = false
                value.value = it
            },
            modifier = Modifier.fillMaxWidth(1f).height(60.dp).semantics{ contentDescription = "email_input_field" },

            textStyle = TextStyle(color = Color.White),
            label = { Text(text = label, color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blue,
                unfocusedBorderColor = Color.White
            ),
            isError = error.isError,
            singleLine = true,
            trailingIcon = {
                if (error.isError)
                    Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
            }
        )
    }
}

@Composable
fun ErrorMessage(errorMessage: String?) {
    Text(
        text = errorMessage ?: "",
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Preview
@Composable
fun SignUpPreview() {
    SignUp(object : AuthViewModel {
        override val uiState: StateFlow<AuthState> = MutableStateFlow(AuthState.ValidationError(
            mapOf("email" to arrayListOf("error message"))))
        override fun createUser(email: String, password: String, passwordConfirmation: String) {}
    })
}