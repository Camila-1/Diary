package com.pchpsky.diary.screens.auth.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.R
import com.pchpsky.diary.composables.AuthButton
import com.pchpsky.diary.composables.TextField
import com.pchpsky.diary.screens.auth.*
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.green

@Composable
fun SignUp(viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val uiState: AuthState by viewModel.uiState.collectAsState()

    fun errorMessageFor(key: String): String? {
        return if (uiState is AuthState.ValidationError)
            (uiState as AuthState.ValidationError).fields[key]?.get(0)
        else null
    }

    if (uiState is AuthState.SignupSuccessful) {
        val context = LocalContext.current
        openHomeScreen(context)
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background),
    ) {
        val (column, button, signup) = createRefs()

        Text(
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .constrainAs(signup) {
                    top.linkTo(parent.top, 35.dp)
                    start.linkTo(parent.start, 40.dp)
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(signup.bottom, 20.dp)
                    start.linkTo(parent.start, 40.dp)
                    end.linkTo(parent.end, 40.dp)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            EmailTextField(email, errorMessageFor(FieldKey.EMAIL.key))
            PasswordTextField(password, errorMessageFor(FieldKey.PASSWORD.key))
            ConfirmPasswordTextField(confirmPassword, errorMessageFor(FieldKey.CONFIRM_PASSWORD.key))
        }

        AuthButton(
            stringResource(R.string.submit),
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, 40.dp)
                    end.linkTo(parent.end, 40.dp)
                    width = Dimension.fillToConstraints
                },
            color = green,
        ) {
            viewModel.createUser(email.value, password.value, confirmPassword.value)
        }
    }
}

@Composable
fun EmailTextField(email: MutableState<String>, errorMessage: String?) {
    TextField(
        email,
        stringResource(R.string.email),
        errorMessage,
        KeyboardType.Email,
        VisualTransformation.None
    )
}

@Composable
fun PasswordTextField(password: MutableState<String>, errorMessage: String?) {
    TextField(
        password,
        stringResource(R.string.password),
        errorMessage,
        KeyboardType.Password,
        PasswordVisualTransformation()
    )
}

@Composable
fun ConfirmPasswordTextField(confirmPassword: MutableState<String>, errorMessage: String?) {
    TextField(
        confirmPassword,
        stringResource(R.string.confirm_password),
        errorMessage,
        KeyboardType.Password,
        PasswordVisualTransformation()
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