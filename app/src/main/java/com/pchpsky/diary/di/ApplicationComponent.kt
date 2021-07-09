package com.pchpsky.diary.di

import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.ui.auth.AuthActivity
import com.pchpsky.diary.datasourse.network.NetworkModule
import com.pchpsky.diary.ui.launch.LaunchActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
}