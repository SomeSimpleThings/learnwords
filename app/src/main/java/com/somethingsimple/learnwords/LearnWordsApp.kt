package com.somethingsimple.learnwords

import android.app.Application
import com.somethingsimple.learnwords.di.application
import com.somethingsimple.learnwords.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LearnWordsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen))
        }
    }
}