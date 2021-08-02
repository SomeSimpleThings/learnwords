package com.somethingsimple.learnwords.ui.words

import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.repo.Repository
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.di.NAME_REMOTE
import com.somethingsimple.learnwords.presenter.Interactor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class WordsInteractor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: Repository<List<Word>>
//    private val localRepository: Repository<List<Word>>
) : Interactor<WordlistState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<WordlistState> {
        return remoteRepository.getData(word)
            .map { WordlistState.Success(it) }
        // if (fromRemoteSource) {
//        } else {
//            localRepository.getData(word).map { WordlistState.Success(it) }
//        }
    }
}
