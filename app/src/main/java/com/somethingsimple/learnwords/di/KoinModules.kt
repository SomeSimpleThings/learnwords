package com.somethingsimple.learnwords.di

import com.somethingsimple.learnwords.data.datasource.remote.RetrofitDataSource
import com.somethingsimple.learnwords.data.repo.Repository
import com.somethingsimple.learnwords.data.repo.RepositoryImpl
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.ui.words.WordsInteractor
import com.somethingsimple.learnwords.ui.words.WordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val application = module {
    single<Repository<List<Word>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitDataSource()) }
}

val mainScreen = module {
    factory { WordsInteractor(get(named(NAME_REMOTE))) }
    viewModel { WordsViewModel(get()) }
}



