package com.somethingsimple.repository.datasource.local

import com.somethingsimple.model.WordlistState
import com.somethingsimple.repository.datasource.DataSource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: WordlistState)
}
