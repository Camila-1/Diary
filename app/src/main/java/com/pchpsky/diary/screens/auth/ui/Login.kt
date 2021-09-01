package com.pchpsky.diary.screens.auth.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.R
import com.pchpsky.diary.composables.AuthButton
import com.pchpsky.diary.composables.ErrorMessage
import com.pchpsky.diary.composables.TextField
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.auth.FakeViewModel
import com.pchpsky.diary.screens.auth.FieldKey
import com.pchpsky.diary.screens.theme.green
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Login(viewModel: AuthViewModel) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val uiState: AuthState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier,
    )

    if (uiState is AuthState.SignupSuccessful) {
        val context = LocalContext.current
        openHomeScreen(context)
    }

    if (uiState is AuthState.AuthenticationError) {
        rememberCoroutineScope().launch(Dispatchers.Main) {
            snackbarHostState.showSnackbar((uiState as AuthState.AuthenticationError).message)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = stringResource(R.string.sign_in), color = Color.White, fontSize = 40.sp)
            LoginTextField(login, null)
            LoginPasswordTextField(password, null)
            AuthButton(
                stringResource(R.string.login),
                onClick = {
                      viewModel.login(login.value, password.value)
                },
                color = green
            )
        }
    }
}

@Composable
fun LoginTextField(login: MutableState<String>, errorMessage: String?) {
    TextField(
        login,
        stringResource(R.string.login),
        errorMessage,
        KeyboardType.Email,
        VisualTransformation.None
    )
    if (errorMessage != null) {
        ErrorMessage(errorMessage)
    }
}

@Composable
fun LoginPasswordTextField(password: MutableState<String>, errorMessage: String?) {
    TextField(
        password,
        stringResource(R.string.password),
        errorMessage,
        KeyboardType.Password,
        PasswordVisualTransformation()
    )
}
@Composable
@Preview
fun LoginPreview() {
    Login(FakeViewModel)
}
