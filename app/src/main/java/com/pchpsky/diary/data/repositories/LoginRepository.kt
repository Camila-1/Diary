package com.pchpsky.diary.data.repositories

import arrow.core.Either
import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.localstorage.TokenStore
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.interfacies.LoginController
import com.pchpsky.schema.CreateSessionMutation

class LoginRepository(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
) : LoginController {
    override suspend fun login(login: String, password: String): Either<NetworkError, CreateSessionMutation.Data?> {
        return networkClient.login(login, password).map {
            it.session?.token?.let { token -> saveToken(token) }
            it
        }
    }

    private suspend fun saveToken(token: String) {
        TokenStore().token = token
        dataStoreManager.saveUserToken(token)
    }
}