package com.pchpsky.diary.data.repositories

import arrow.core.Either
import com.pchpsky.diary.data.localstorage.DataStoreManager
import com.pchpsky.diary.data.localstorage.TokenStore
import com.pchpsky.diary.data.network.NetworkClient
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.data.repositories.interfacies.SignupController
import com.pchpsky.schema.CreateUserMutation

class SignupRepository(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
) : SignupController {
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