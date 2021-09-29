package com.pchpsky.diary.screens.auth.interfaces

import com.pchpsky.diary.screens.auth.AuthState
import kotlinx.coroutines.flow.StateFlow

interface Signup {
    val uiState: StateFlow<AuthState>

    suspend fun createUser(email: String, password: String, passwordConfirmation: String)
}