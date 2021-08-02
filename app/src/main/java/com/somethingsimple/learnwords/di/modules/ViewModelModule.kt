package com.somethingsimple.learnwords.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.somethingsimple.learnwords.di.ViewModelKey
import com.somethingsimple.learnwords.ui.words.WordsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WordsViewModel::class)
    protected abstract fun wordsViewModel(wordsViewModel: WordsViewModel): ViewModel
}
