package com.pchpsky.diary.screens.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.RoundedFilledButton
import com.pchpsky.diary.components.LogoGroup
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.green
import com.pchpsky.diary.theme.lightGreen
import com.pchpsky.diary.R

@Composable
fun StartScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(250.dp)
        ) {
            LogoGroup(modifier = Modifier.padding(bottom = 40.dp))

            RoundedFilledButton(
                stringResource(R.string.login),
                modifier = Modifier.fillMaxWidth().testTag("login_button"),
                color = lightGreen,
                onClick = {
                    navController.navigate(AuthRoute.LOGIN.route)
                })
            RoundedFilledButton(
                text = stringResource(R.string.sign_up),
                modifier = Modifier.fillMaxWidth().testTag("signup_button"),
                color = green,
                onClick = { navController.navigate(AuthRoute.SIGNUP.route) })
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    DiaryTheme {
        StartScreen(rememberNavController())
    }
}
