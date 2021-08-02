package com.somethingsimple.learnwords.data.repo

interface Repository<T> {

    suspend fun getData(word: String): T
}