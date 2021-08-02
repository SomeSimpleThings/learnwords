package com.somethingsimple.learnwords.di

import com.somethingsimple.learnwords.MainActivity
import com.somethingsimple.learnwords.di.modules.InteractorModule
import com.somethingsimple.learnwords.di.modules.ViewModelModule
import com.somethingsimple.learnwords.ui.words.list.WordlistFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules =
    [
        InteractorModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface LearnWordsComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: WordlistFragment)
}