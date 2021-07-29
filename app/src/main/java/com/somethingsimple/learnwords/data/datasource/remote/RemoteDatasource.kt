package com.somethingsimple.learnwords.data.datasource.remote

import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.vo.Word
import io.reactivex.rxjava3.core.Observable

class RemoteDatasource(private val remoteProvider: DataSource<List<Word>> = RetrofitDataSource()) :
    DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> =
        remoteProvider
            .getData(word)
}