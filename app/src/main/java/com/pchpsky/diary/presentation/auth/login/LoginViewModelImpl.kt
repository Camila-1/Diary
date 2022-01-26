package com.pchpsky.diary.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.LoginRepository
import com.pchpsky.diary.data.repositories.interfacies.LoginController
import com.pchpsky.diary.presentation.auth.AuthState
import com.pchpsky.diary.presentation.auth.login.interfacies.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val loginRepository: LoginController) :
    LoginViewModel, ViewModel() {
    private val _uiState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.None)

    override val uiState: StateFlow<AuthState> = _uiState

    override suspend fun login(login: String, password: String) {
        _uiState.value = AuthState.Loading

        loginRepository.login(login, password).fold(
            {
                when(it) {
                    is NetworkError.ServerError -> { AuthState.ServerError }
                    is NetworkError.AuthenticationError -> { AuthState.AuthenticationError(it.message) }
                    is NetworkError.ValidationError -> { AuthState.ValidationError(it.fields) }
                }
            },
            {
                AuthState.SignupSuccessful
            }
        ).also { _uiState.value = it }
    }
}

object FakeAuthViewModel : LoginViewModel {
    override val uiState: StateFlow<AuthState> = MutableStateFlow(AuthState.ValidationError(emptyMap()))
    override suspend fun login(login: String, password: String) {}
}