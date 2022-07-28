package com.pchpsky.diary.presentation.ui.auth.signup.interfacies

import com.pchpsky.diary.presentation.auth.AuthState
import kotlinx.coroutines.flow.StateFlow

interface SignupViewModel {
    val uiState: StateFlow<AuthState>

    suspend fun createUser(email: String, password: String, passwordConfirmation: String)
}