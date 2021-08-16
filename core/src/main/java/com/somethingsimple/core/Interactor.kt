package com.somethingsimple.core


interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}