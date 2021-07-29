package com.somethingsimple.learnwords.presenter

import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.ui.base.View

interface Presenter<T : WordlistState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
