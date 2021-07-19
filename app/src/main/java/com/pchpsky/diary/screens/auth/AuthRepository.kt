package com.pchpsky.diary.screens.auth

import com.pchpsky.schema.CreateUserMutation

interface AuthRepository {

    fun createUser(email: String, password: String): CreateUserMutation.Data?
}