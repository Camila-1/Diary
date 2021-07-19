package com.pchpsky.diary.screens.auth.signup

sealed class SignupState {
    object ValidationError : SignupState()
    object EmptyFieldError : SignupState()
    object Loading : SignupState()
    object SignupSuccessful : SignupState()
}
