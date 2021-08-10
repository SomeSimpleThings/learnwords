package com.somethingsimple.history

import androidx.lifecycle.LiveData
import com.somethingsimple.core.vm.BaseViewModel
import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<WordlistState>() {

    private val liveDataForViewToObserve: LiveData<WordlistState> = _mutableLiveData

    fun subscribe(): LiveData<WordlistState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = WordlistState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(WordlistState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value =
            WordlistState.Success(null)//TODO Workaround. Set View to original state
        super.onCleared()
    }

    fun parseLocalSearchResults(data: WordlistState): WordlistState {
        return WordlistState.Success(mapResult(data))
    }

    private fun mapResult(
        data: WordlistState,
    ): List<Word> {
        val newSearchResults = arrayListOf<Word>()
        when (data) {
            is WordlistState.Success -> {
                getSuccessResultData(data, newSearchResults)
            }
        }
        return newSearchResults
    }

    private fun getSuccessResultData(
        data: WordlistState.Success,
        newDataModels: ArrayList<Word>
    ) {
        val dataModels: List<Word> = data.data as List<Word>
        if (dataModels.isNotEmpty()) {

            for (searchResult in dataModels) {
                newDataModels.add(Word(0, arrayListOf(), searchResult.text))
            }
        }
    }
}
