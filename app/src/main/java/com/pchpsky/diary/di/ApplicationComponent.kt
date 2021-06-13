package com.pchpsky.diary.di

import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.auth.AuthActivity
import com.pchpsky.diary.launch.LaunchActivity
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
}