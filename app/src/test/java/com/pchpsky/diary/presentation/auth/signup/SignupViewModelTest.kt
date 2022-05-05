package com.pchpsky.diary.presentation.auth.signup

import arrow.core.Either
import com.apollographql.apollo.api.Operation
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.interfacies.SignupController
import com.pchpsky.diary.presentation.auth.AuthState
import com.pchpsky.schema.CreateUserMutation
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SignupViewModelTest {
    private fun repository(response: Either<NetworkError, Operation.Data>): SignupController {
        return object : SignupController {
            override suspend fun createUserAndSaveUserToken(
                email: String,
                password: String
            ): Either<NetworkError, CreateUserMutation.Data?> = response as Either<NetworkError, CreateUserMutation.Data?>
        }
    }

    @Test
    fun createUser_CreateUserRequest_UserSignedUp() = runBlocking {
        val repository = repository(Either.Right(CreateUserMutation.Data(session = null)))
        val viewModel = SignupViewModelImpl(repository)
        val state = viewModel.uiState
        val password = "password"

        viewModel.createUser("email", password, password)
        Assert.assertEquals(AuthState.SignupSuccessful, state.value)
    }

    @Test
    fun createUser_DoesNotMatchPassword_ValidationError() = runBlocking {
        val repository = repository(Either.Right(CreateUserMutation.Data(session = null)))
        val viewModel = SignupViewModelImpl(repository)
        val state = viewModel.uiState

        viewModel.createUser("email", "password", "mismatched password")
        Assert.assertEquals(AuthState.ValidationError(mapOf("confirm password" to arrayListOf("Does not mach password"))), state.value)
    }
}