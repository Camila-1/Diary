package com.pchpsky.diary.datasource.localstorage

class TokenStore {

    var token: String?
        get() = TokenStore.token
        set(value) {
            TokenStore.token = value
        }

    companion object {
        private var token: String? = null
    }
}