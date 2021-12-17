package com.pchpsky.diary.screens.auth

import arrow.core.Either
import arrow.core.Right
import com.apollographql.apollo.api.Operation
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation
import org.junit.Test
import com.pchpsky.diary.screens.auth.interfaces.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals


class AuthViewModelTest {

    private fun repository(response: Either<NetworkError, Operation.Data>): AuthController {
        return object : AuthController {
            override suspend fun login(
                login: String,
                password: String
            ): Either<NetworkError, CreateSessionMutation.Data?> = response as Either<NetworkError, CreateSessionMutation.Data?>

            override suspend fun createUserAndSaveUserToken(
                email: String,
                password: String
            ): Either<NetworkError, CreateUserMutation.Data?> = response as Either<NetworkError, CreateUserMutation.Data?>
        }
    }

    @Test
    fun login_LoginRequest_UserLoggedIn() = runBlocking {

        val repository = repository(Right(CreateSessionMutation.Data(session = null)))
        val viewModel = AuthViewModel(repository)
        val state = viewModel.uiState

        viewModel.login("login", "password")

        assertEquals(AuthState.SignupSuccessful, state.value )
    }

    @Test
    fun createUser_CreateUserRequest_UserSignedUp() = runBlocking {
        val repository = repository(Right(CreateUserMutation.Data(session = null)))
        val viewModel = AuthViewModel(repository)
        val state = viewModel.uiState
        val password = "password"

        viewModel.createUser("email", password, password)
        assertEquals(AuthState.SignupSuccessful, state.value)
    }

    @Test
    fun createUser_DoesNotMatchPassword_ValidationError() = runBlocking {
        val repository = repository(Right(CreateUserMutation.Data(session = null)))
        val viewModel = AuthViewModel(repository)
        val state = viewModel.uiState

        viewModel.createUser("email", "password", "mismatched password")
        assertEquals(AuthState.ValidationError(mapOf("confirm password" to arrayListOf("Does not mach password"))), state.value)
    }
}