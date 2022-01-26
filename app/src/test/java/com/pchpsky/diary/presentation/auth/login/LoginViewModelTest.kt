package com.pchpsky.diary.presentation.auth.login

import arrow.core.Either
import arrow.core.Right
import com.apollographql.apollo.api.Operation
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.interfacies.LoginController
import com.pchpsky.diary.presentation.auth.AuthState
import com.pchpsky.schema.CreateSessionMutation
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class LoginViewModelTest {

    private fun repository(response: Either<NetworkError, Operation.Data>): LoginController {
        return object : LoginController {
            override suspend fun login(
                login: String,
                password: String
            ): Either<NetworkError, CreateSessionMutation.Data?> = response as Either<NetworkError, CreateSessionMutation.Data?>
        }
    }

    @Test
    fun login_LoginRequest_UserLoggedIn() = runBlocking {

        val repository = repository(Right(CreateSessionMutation.Data(session = null)))
        val viewModel = LoginViewModelImpl(repository)
        val state = viewModel.uiState

        viewModel.login("login", "password")

        Assert.assertEquals(AuthState.SignupSuccessful, state.value )
    }
}