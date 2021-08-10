package com.somethingsimple.core

import androidx.fragment.app.Fragment
import com.somethingsimple.model.WordlistState

abstract class BaseFragment<T : WordlistState> : Fragment(), View {
    abstract override fun renderData(appState: WordlistState)
}
