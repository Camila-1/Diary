package com.pchpsky.diary.presentation.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.R
import com.pchpsky.diary.presentation.components.LogoGroup
import com.pchpsky.diary.presentation.components.RoundedFilledButton
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.green
import com.pchpsky.diary.presentation.theme.lightGreen

@Composable
fun StartScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToSignupScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
            .semantics { contentDescription = "Start screen" },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(250.dp)
        ) {
            LogoGroup(modifier = Modifier.padding(bottom = 40.dp))

            RoundedFilledButton(
                stringResource(R.string.login),
                modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Login button" },
                color = lightGreen,
                onClick = { navigateToLoginScreen() }
            )
            RoundedFilledButton(
                text = stringResource(R.string.sign_up),
                modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Signup button" },
                color = green,
                onClick = { navigateToSignupScreen() }
            )
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    DiaryTheme {
        StartScreen({}, {})
    }
}
