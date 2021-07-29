package com.somethingsimple.learnwords.data.repo

import io.reactivex.rxjava3.core.Observable

interface Repository<T> {

    fun getData(word: String): Observable<T>
}