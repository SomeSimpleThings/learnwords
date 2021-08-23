package com.somethingsimple.learnwords.di

import androidx.room.Room
import com.somethingsimple.learnwords.ui.words.WordsInteractor
import com.somethingsimple.learnwords.ui.words.WordsViewModel
import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.Repository
import com.somethingsimple.repository.RepositoryImpl
import com.somethingsimple.repository.RepositoryLocal
import com.somethingsimple.repository.RepositoryLocalImpl
import com.somethingsimple.repository.datasource.local.RoomImplementation
import com.somethingsimple.repository.datasource.local.storage.HistoryDataBase
import com.somethingsimple.repository.datasource.remote.RetrofitDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val application = module {
    single<Repository<List<Word>>>(named(NAME_REMOTE)) {
        RepositoryImpl(RetrofitDataSource())
    }
    single<RepositoryLocal<List<Word>>>(named(NAME_LOCAL)) {
        RepositoryLocalImpl(RoomImplementation(get()))
    }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "cache.db").build() }
    single { get<HistoryDataBase>().historyDao() }

}

val mainScreen = module {
    factory { WordsInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    viewModel { WordsViewModel(get()) }
}
