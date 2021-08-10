package com.somethingsimple.repository.datasource.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.somethingsimple.repository.datasource.remote.api.SkyengApi
import com.somethingsimple.repository.datasource.DataSource
import com.somethingsimple.model.vo.Word
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSource() :
    DataSource<List<Word>> {

    private val skyengApi: SkyengApi

    init {
        skyengApi = provideSkyengApi(provideOkHttpClient())
    }

    override suspend fun getData(word: String): List<Word> =
        skyengApi.search(word)

    private fun provideSkyengApi(okHttpClient: OkHttpClient): SkyengApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(SkyengApi::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }

}