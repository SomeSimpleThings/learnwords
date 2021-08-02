package com.somethingsimple.learnwords.presenter


interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}