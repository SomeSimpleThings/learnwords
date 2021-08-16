package com.somethingsimple.repository.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}