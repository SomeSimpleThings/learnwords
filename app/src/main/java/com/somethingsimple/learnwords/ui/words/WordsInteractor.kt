package com.somethingsimple.learnwords.ui.words

import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.repo.Repository
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.presenter.Interactor

class WordsInteractor(
    private val remoteRepository: Repository<List<Word>>
//    private val localRepository: Repository<List<Word>>
) : Interactor<WordlistState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): WordlistState =
        WordlistState.Success(remoteRepository.getData(word))

}
