package com.pchpsky.diary.screens.auth

import arrow.core.Either
import com.pchpsky.diary.datasource.localstorage.DataStoreManager
import com.pchpsky.diary.datasource.localstorage.TokenStore
import com.pchpsky.diary.datasource.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.screens.auth.interfaces.LoginRepository
import com.pchpsky.diary.screens.auth.interfaces.SignupRepository
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
    ) : LoginRepository, SignupRepository {

    override suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?> {
        return networkClient.login(login, password).map {
            it.session?.token?.let { token -> saveToken(token) }
            it
        }
    }

    override suspend fun createUser(email: String, password: String): Either<NetworkError, CreateUserMutation.Data?> {
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