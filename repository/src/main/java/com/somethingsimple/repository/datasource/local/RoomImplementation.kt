package com.somethingsimple.repository.datasource.local

import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.datasource.local.storage.convertDataModelSuccessToEntity
import com.somethingsimple.repository.datasource.local.storage.dao.HistoryDao
import com.somethingsimple.repository.datasource.local.storage.mapHistoryEntityToSearchResult

class RoomImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: WordlistState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
