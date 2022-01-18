package com.pchpsky.diary.presentation.auth

import arrow.core.Either
import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.localstorage.TokenStore
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.presentation.auth.interfaces.AuthController
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
    ) : AuthController {

    override suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?> {
        return networkClient.login(login, password).map {
            it.session?.token?.let { token -> saveToken(token) }
            it
        }
    }

    override suspend fun createUserAndSaveUserToken(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?> {
        return networkClient.createUser(email, password).map {
            it.session?.token?.let { token -> saveToken(token) }
            it
        }
    }

    private suspend fun saveToken(token: String) {
        TokenStore().token = token
        dataStoreManager.saveUserToken(token)
    }
}