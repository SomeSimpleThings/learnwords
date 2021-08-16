package com.somethingsimple.repository.datasource.local.storage

import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<Word> {
    val searchResult = ArrayList<Word>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(Word(0, null, entity.word))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(wordlistState: WordlistState): HistoryEntity? {
    return when (wordlistState) {
        is WordlistState.Success -> {
            val searchResult = wordlistState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}
