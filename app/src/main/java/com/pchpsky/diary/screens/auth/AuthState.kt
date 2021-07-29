package com.pchpsky.diary.screens.auth

sealed class AuthState {
    object EmptyFieldError : AuthState()
    object Loading : AuthState()
    object SignupSuccessful : AuthState()
    object None : AuthState()
    data class ValidationError(val fields: Map<String, ArrayList<String>>) : AuthState()
}
