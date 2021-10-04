package com.pchpsky.diary.screens.launch

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.datasource.localstorage.TokenStore
import com.pchpsky.diary.screens.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchActivity : ComponentActivity() {

    private val viewModel: AppLaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            val token = viewModel.token().also { TokenStore().token = it }
            if (token != null) startActivity(Intent(applicationContext, MainActivity::class.java))
            else startActivity(Intent(applicationContext, AuthActivity::class.java))
            finish()
        }
    }
}