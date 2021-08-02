package com.somethingsimple.learnwords.di.modules

import com.google.gson.GsonBuilder
import com.somethingsimple.learnwords.data.api.SkyengApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSkyengApi(okHttpClient: OkHttpClient): SkyengApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SkyengApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}