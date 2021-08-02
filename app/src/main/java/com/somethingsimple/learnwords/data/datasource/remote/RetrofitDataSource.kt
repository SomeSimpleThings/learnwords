package com.somethingsimple.learnwords.data.datasource.remote

import com.somethingsimple.learnwords.data.api.SkyengApi
import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.vo.Word
import io.reactivex.rxjava3.core.Observable

class RetrofitDataSource(private val skyengApi: SkyengApi) :
    DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> =
        skyengApi.search(word)

}