package com.pchpsky.diary.screens.auth.ui

import androidx.compose.foundation.background
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
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.theme.blue
import com.pchpsky.diary.screens.theme.green
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SignUp(viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val uiState: AuthState by viewModel?.uiState?.collectAsState()!!

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EmailInputField(email, uiState)
            PasswordInputField(password)
            ConfirmPasswordInputField()
            SignUpButton(
                "Submit",
                onClick = { viewModel?.createUser(email.value, password.value) },
                color = green
            )
        }
    }
}

@Composable
fun EmailInputField(value: MutableState<String>, state: AuthState) {

    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp),
        textStyle = TextStyle(color = Color.White),
        label = { Text(text = "Email", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        ),
        isError = state is AuthState.ValidationError,
        singleLine = true
    )
}

@Composable
fun PasswordInputField(value: MutableState<String>) {

    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp),
        textStyle = TextStyle(color = Color.White),
        label = { Text(text = "Password", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        ),
    )
}

@Composable
fun ConfirmPasswordInputField() {
    var email by remember { mutableStateOf("") }


    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        modifier = Modifier.fillMaxWidth(1f).height(60.dp),
        textStyle = TextStyle(color = Color.White),
        label = { Text(text = "Confirm Password", color = Color.White) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = Color.White
        )
    )
}

@Composable
fun SignUpButton(text: String, color: Color, onClick: () -> Unit) {
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
@Preview
@Composable
fun SignUpPreview() {
    SignUp(object : AuthViewModel {
        override val uiState: StateFlow<AuthState> = MutableStateFlow(AuthState.ValidationError(emptyMap()))
        override fun createUser(email: String, password: String) {}
    })
}