package com.somethingsimple.learnwords.ui.words

import androidx.lifecycle.LiveData
import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.vo.Meaning
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.vm.BaseViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class WordsViewModel
@Inject constructor(
    private val interactor: WordsInteractor
) : BaseViewModel<WordlistState>() {

    private var appState: WordlistState? = null

    fun subscribe(): LiveData<WordlistState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = WordlistState.Loading(null) }

    private fun getObserver(): DisposableObserver<WordlistState> {
        return object : DisposableObserver<WordlistState>() {

            override fun onNext(state: WordlistState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = WordlistState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

    fun parseSearchResults(state: WordlistState): WordlistState {
        val newSearchResults = arrayListOf<Word>()
        when (state) {
            is WordlistState.Success -> {
                val searchResults = state.data
                if (!searchResults.isNullOrEmpty()) {
                    for (searchResult in searchResults) {
                        parseResult(searchResult, newSearchResults)
                    }
                }
            }
        }

        return WordlistState.Success(newSearchResults)
    }

    private fun parseResult(dataModel: Word, newDataModels: ArrayList<Word>) {
        if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
            val newMeanings = arrayListOf<Meaning>()
            for (meaning in dataModel.meanings) {
                if (meaning.translation != null && !meaning.translation.text.isNullOrBlank()) {
                    newMeanings.add(
                        Meaning(
                            0,
                            meaning.imageUrl,
                            null,
                            null,
                            null,
                            null,
                            meaning.translation
                        )
                    )
                }
            }
            if (newMeanings.isNotEmpty()) {
                newDataModels.add(Word(0, newMeanings, dataModel.text))
            }
        }
    }
}
