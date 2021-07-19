package com.pchpsky.diary.screens.auth

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.screens.auth.signup.SignupState
import com.pchpsky.schema.CreateUserMutation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppAuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(), AuthViewModel {

    override fun createUser(email: String, password: String): CreateUserMutation.Data? {
        val b = repository.createUser(email, password)
        return b
    }
}
