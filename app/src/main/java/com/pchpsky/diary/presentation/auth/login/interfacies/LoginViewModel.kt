package com.pchpsky.diary.presentation.auth.login.interfacies

import com.pchpsky.diary.presentation.auth.AuthState
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val uiState: StateFlow<AuthState>
    suspend fun login(login: String, password: String)
}