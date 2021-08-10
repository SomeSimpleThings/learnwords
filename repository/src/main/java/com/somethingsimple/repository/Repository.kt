package com.somethingsimple.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}