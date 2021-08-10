package com.somethingsimple.repository

import com.somethingsimple.model.WordlistState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: WordlistState)
}
