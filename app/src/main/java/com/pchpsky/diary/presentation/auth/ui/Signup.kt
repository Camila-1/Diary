package com.pchpsky.diary.presentation.auth.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.imePadding
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.R
import com.pchpsky.diary.presentation.auth.*
import com.pchpsky.diary.presentation.auth.interfaces.SignupViewModel
import com.pchpsky.diary.presentation.components.OutlinedTextField
import com.pchpsky.diary.presentation.components.RoundedFilledButton
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.green
import kotlinx.coroutines.launch


@Composable
fun SignUp(viewModel: SignupViewModel = hiltViewModel<AuthViewModel>()) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val uiState: AuthState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

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
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(DiaryTheme.colors.background)
            .semantics { contentDescription = "Signup screen" },
    ) {
        val (column, button, signUpTitle) = createRefs()

        Text(
            text = stringResource(R.string.sign_up),
            style = DiaryTheme.typography.authScreenHeader,
            modifier = Modifier
                .constrainAs(signUpTitle) {
                    top.linkTo(parent.top, 40.dp)
                    start.linkTo(parent.start, 40.dp)
                },
            color = DiaryTheme.colors.text
        )

        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(signUpTitle.bottom, 20.dp)
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

        RoundedFilledButton(
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
            scope.launch {
                viewModel.createUser(email.value, password.value, confirmPassword.value)
            }
        }
    }
}

@Composable
fun EmailTextField(email: MutableState<String>, errorMessage: String?) {
    OutlinedTextField(
        email,
        stringResource(R.string.email),
        errorMessage,
        KeyboardType.Email,
        VisualTransformation.None
    )
}

@Composable
fun PasswordTextField(password: MutableState<String>, errorMessage: String?) {
    OutlinedTextField(
        password,
        stringResource(R.string.password),
        errorMessage,
        KeyboardType.Password,
        PasswordVisualTransformation()
    )
}

@Composable
fun ConfirmPasswordTextField(confirmPassword: MutableState<String>, errorMessage: String?) {
    OutlinedTextField(
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
    DiaryTheme(darkTheme = true) {
        SignUp(FakeAuthViewModel)
    }
}