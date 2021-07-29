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
        repository.createUser(email, password).fold(
            {
                when(it) {
                    is NetworkError.ServerError -> {}
                    is NetworkError.AuthenticationError -> {

                    }
                    is NetworkError.ValidationError -> {
                        authState.value = AuthState.ValidationError(it.fields)
                    }
                    else -> {}
                }
            },
            {
                authState.value = AuthState.SignupSuccessful
            }
        )
    }
}
