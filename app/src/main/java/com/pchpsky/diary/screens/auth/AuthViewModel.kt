package com.pchpsky.diary.screens.auth

import kotlinx.coroutines.flow.StateFlow

interface AuthViewModel {

    val uiState: StateFlow<AuthState>

    fun createUser(email: String, password: String,passwordConfirmation: String)
    fun login(login: String, password: String)
}