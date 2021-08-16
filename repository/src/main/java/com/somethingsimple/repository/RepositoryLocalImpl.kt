package com.somethingsimple.repository

import com.somethingsimple.model.WordlistState
import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.datasource.local.DataSourceLocal

class RepositoryLocalImpl(private val dataSource: DataSourceLocal<List<Word>>) :
    RepositoryLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: WordlistState) {
        dataSource.saveToDB(appState)
    }
}
