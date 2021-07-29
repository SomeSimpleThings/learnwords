package com.somethingsimple.learnwords.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.presenter.Presenter

abstract class BaseFragment<T : WordlistState> : Fragment(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: WordlistState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
