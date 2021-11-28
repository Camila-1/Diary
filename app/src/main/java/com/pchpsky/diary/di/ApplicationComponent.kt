package com.pchpsky.diary.di

import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.screens.auth.AuthActivity
import com.pchpsky.diary.datasource.network.NetworkModule
import com.pchpsky.diary.screens.launch.LaunchActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
}