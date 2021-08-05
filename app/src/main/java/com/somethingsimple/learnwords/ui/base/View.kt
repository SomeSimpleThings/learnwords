package com.somethingsimple.learnwords.ui.base

import com.somethingsimple.learnwords.data.WordlistState

interface View {

    fun renderData(appState: WordlistState)

}