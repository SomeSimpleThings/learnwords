package com.somethingsimple.learnwords

import android.app.Application
import com.somethingsimple.learnwords.di.DaggerLearnWordsComponent
import com.somethingsimple.learnwords.di.LearnWordsComponent

class LearnWordsApp : Application() {
    val appComponent: LearnWordsComponent = DaggerLearnWordsComponent.create()
}