package com.somethingsimple.learnwords

import android.app.Application
import com.somethingsimple.learnwords.di.application
import com.somethingsimple.learnwords.di.mainScreen
import org.koin.core.context.startKoin

class LearnWordsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}