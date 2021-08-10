package com.somethingsimple.history

import com.somethingsimple.core.Interactor
import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<WordlistState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): WordlistState {
        return WordlistState.Success(
            repositoryLocal.getData(word)
        )
    }
}
