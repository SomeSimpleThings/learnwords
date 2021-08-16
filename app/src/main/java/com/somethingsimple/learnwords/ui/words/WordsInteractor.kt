package com.somethingsimple.learnwords.ui.words

import com.somethingsimple.core.Interactor
import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.Repository
import com.somethingsimple.repository.RepositoryLocal

class WordsInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: RepositoryLocal<List<Word>>
) : Interactor<WordlistState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): WordlistState {
        val appState: WordlistState
        if (fromRemoteSource) {
            appState = WordlistState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = WordlistState.Success(localRepository.getData(word))
        }
        return appState
    }

}
