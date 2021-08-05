package com.somethingsimple.learnwords.data.repo

import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.vo.Word

class RepositoryImpl(private val dataSource: DataSource<List<Word>>) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> =
        dataSource.getData(word)
}