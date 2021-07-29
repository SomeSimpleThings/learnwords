package com.somethingsimple.learnwords.data.repo

import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.vo.Word
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(private val dataSource: DataSource<List<Word>>) : Repository<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> =
        dataSource.getData(word)
}