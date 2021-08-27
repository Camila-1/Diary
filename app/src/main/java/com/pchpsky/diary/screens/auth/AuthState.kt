package com.pchpsky.diary.screens.auth

sealed class AuthState {
    object Loading : AuthState()
    object SignupSuccessful : AuthState()
    object None : AuthState()
    object ServerError : AuthState()
    object AuthenticationError : AuthState()
    data class ValidationError(val fields: Map<String, ArrayList<String>>) : AuthState()
}
