package com.pchpsky.diary.screens.auth

import com.pchpsky.schema.CreateUserMutation

interface AuthViewModel {
    fun createUser(email: String, password: String): CreateUserMutation.Data?
}