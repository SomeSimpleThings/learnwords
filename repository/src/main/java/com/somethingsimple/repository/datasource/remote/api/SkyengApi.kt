package com.somethingsimple.repository.datasource.remote.api

import com.somethingsimple.model.vo.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("words/search")
    suspend fun search(@Query("search") word: String): List<Word>
}
