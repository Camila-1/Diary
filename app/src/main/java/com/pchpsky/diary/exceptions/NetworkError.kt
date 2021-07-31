package com.pchpsky.diary.exceptions

sealed class NetworkError {
    object ServerError : NetworkError()
    object AuthenticationError : NetworkError()
    object SignupSuccessful : NetworkError()
    data class ValidationError(val fields: Map<String, ArrayList<String>>) : NetworkError()
}