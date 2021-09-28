package com.pchpsky.diary.screens.launch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : ComponentActivity() {

    private val viewModel: AppLaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaunchScreen()
        }

//        CoroutineScope(Dispatchers.Main).launch {
//            val token = viewModel.token()
//            if (token != null) startActivity(Intent(applicationContext, MainActivity::class.java))
//            else startActivity(Intent(applicationContext, AuthActivity::class.java))
//            finish()
//        }
    }
}