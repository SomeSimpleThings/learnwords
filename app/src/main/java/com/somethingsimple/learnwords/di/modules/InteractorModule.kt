package com.somethingsimple.learnwords.di.modules

import com.somethingsimple.learnwords.data.repo.Repository
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.di.NAME_REMOTE
import com.somethingsimple.learnwords.ui.words.WordsInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [RepositoryModule::class])
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<Word>>,
    ) = WordsInteractor(repositoryRemote)
}
