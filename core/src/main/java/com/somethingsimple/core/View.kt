package com.somethingsimple.core

import com.somethingsimple.model.WordlistState

interface View {

    fun renderData(appState: WordlistState)

}