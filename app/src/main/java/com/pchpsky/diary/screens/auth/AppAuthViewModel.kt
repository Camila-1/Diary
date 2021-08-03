package com.pchpsky.diary.screens.auth

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.exceptions.NetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppAuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(), AuthViewModel {

    private val authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.None)

    override val uiState: StateFlow<AuthState> = authState

    override fun createUser(email: String, password: String) {
        authState.value = AuthState.Loading

        repository.createUser(email, password).fold(
            {
                when(it) {
                    is NetworkError.ServerError -> { AuthState.ServerError }
                    is NetworkError.AuthenticationError -> { AuthState.AuthenticationError }
                    is NetworkError.ValidationError -> { AuthState.ValidationError(it.fields) }
                }
            },
            {
                AuthState.SignupSuccessful
            }
        ).also { authState.value = it }
    }
}
