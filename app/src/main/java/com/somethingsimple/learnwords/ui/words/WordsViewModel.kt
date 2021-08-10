package com.somethingsimple.learnwords.ui.words

import androidx.lifecycle.LiveData
import com.somethingsimple.core.vm.BaseViewModel
import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Meaning
import com.somethingsimple.model.vo.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordsViewModel(private val interactor: WordsInteractor) :
    BaseViewModel<WordlistState>() {

    private val liveDataForViewToObserve: LiveData<WordlistState> =
        _mutableLiveData

    fun subscribe(): LiveData<WordlistState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = WordlistState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(WordlistState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = WordlistState.Success(null)
        super.onCleared()
    }

    fun parseSearchResults(data: WordlistState): WordlistState {
        val newSearchResults = arrayListOf<Word>()
        when (data) {
            is WordlistState.Success -> {
                val searchResults = data.data
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
            for (meaning in dataModel.meanings!!) {
                if (meaning.translation != null && !meaning.translation!!.text.isNullOrBlank()) {
                    newMeanings.add(
                        Meaning(
                            0,
                            meaning.imageUrl,
                            null,
                            null,
                            null,
                            null,
                            meaning.translation,
                        )
                    )
                }
            }
            if (newMeanings.isNotEmpty()) {
                newDataModels.add(Word(0, newMeanings, dataModel.text))
            }
        }
    }

    fun convertMeaningsToString(meanings: List<Meaning>): String {
        var meaningsSeparatedByComma = String()
        for ((index, meaning) in meanings.withIndex()) {
            meaningsSeparatedByComma += if (index + 1 != meanings.size) {
                String.format("%s%s", meaning.translation?.text, ", ")
            } else {
                meaning.translation?.text
            }
        }
        return meaningsSeparatedByComma
    }
}

