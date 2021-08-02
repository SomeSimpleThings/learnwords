package com.somethingsimple.learnwords.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.somethingsimple.learnwords.LearnWordsApp
import com.somethingsimple.learnwords.data.WordlistState

abstract class BaseFragment<T : WordlistState> : Fragment(), View {


    abstract override fun renderData(appState: WordlistState)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
