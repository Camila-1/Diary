package com.pchpsky.diary.screens.auth.interfaces

import com.pchpsky.diary.screens.auth.AuthState
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val uiState: StateFlow<AuthState>

    suspend fun login(login: String, password: String)
}