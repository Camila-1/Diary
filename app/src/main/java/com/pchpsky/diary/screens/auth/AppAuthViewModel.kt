package com.pchpsky.diary.screens.auth

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.exceptions.NetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppAuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(), AuthViewModel {

    private val _uiState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.None)

    override val uiState: StateFlow<AuthState> = _uiState

    override fun createUser(email: String, password: String, passwordConfirmation: String) {
        if (!password.contentEquals(passwordConfirmation)) {
            _uiState.value = AuthState.ValidationError(mapOf("confirm password" to arrayListOf("Does not mach password")))
            return
        }
        _uiState.value = AuthState.Loading

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
        ).also { _uiState.value = it }
    }
}


object FakeViewModel : AuthViewModel {
    override val uiState: StateFlow<AuthState> = MutableStateFlow(AuthState.ValidationError(emptyMap()))
    override fun createUser(email: String, password: String, passwordConfirmation: String) {}
}