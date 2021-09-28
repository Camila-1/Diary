package com.pchpsky.diary.screens.auth

import kotlinx.coroutines.flow.StateFlow

interface AuthViewModel {

    val uiState: StateFlow<AuthState>

    suspend fun createUser(email: String, password: String, passwordConfirmation: String)
    suspend fun login(login: String, password: String)
}