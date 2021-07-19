package com.pchpsky.diary.screens.auth

import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.schema.CreateUserMutation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AppAuthRepository @Inject constructor(private val networkClient: NetworkClient) : AuthRepository {

    override fun createUser(email: String, password: String): CreateUserMutation.Data? {
        return runBlocking(Dispatchers.IO) {
            networkClient.createUser(email, password)
        }
    }
}