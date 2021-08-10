package com.somethingsimple.repository

import com.somethingsimple.model.vo.Word
import com.somethingsimple.repository.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<Word>>) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> =
        dataSource.getData(word)
}