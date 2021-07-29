package com.somethingsimple.learnwords.data.datasource.remote

import com.somethingsimple.learnwords.data.api.SkyengApi
import com.somethingsimple.learnwords.data.api.SkyengRetrofitClent
import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.vo.Word
import io.reactivex.rxjava3.core.Observable

class RetrofitDataSource(private val client: SkyengRetrofitClent = SkyengRetrofitClent()) :
    DataSource<List<Word>> {

    private val skyengApi: SkyengApi
        get() = client.getRetrofitImpl()

    override fun getData(word: String): Observable<List<Word>> =
        skyengApi.search(word)

}