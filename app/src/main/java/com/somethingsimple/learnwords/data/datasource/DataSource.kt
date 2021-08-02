package com.somethingsimple.learnwords.data.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}