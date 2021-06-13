package com.pchpsky.diary

import android.app.Application
import com.pchpsky.diary.di.DaggerApplicationComponent

class DiaryApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()

}