package com.somethingsimple.learnwords.ui.words

import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.datasource.remote.RemoteDatasource
import com.somethingsimple.learnwords.data.repo.RepositoryImpl
import com.somethingsimple.learnwords.presenter.Presenter
import com.somethingsimple.learnwords.schedulers.RxSchedulers
import com.somethingsimple.learnwords.schedulers.RxSchedulersImpl
import com.somethingsimple.learnwords.ui.base.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class WordsPresenter<T : WordlistState, V : View>(
    private val interactor: WordsInteractor = WordsInteractor(
        RepositoryImpl(RemoteDatasource())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulers: RxSchedulers = RxSchedulersImpl
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSubscribe { currentView?.renderData(WordlistState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<WordlistState> {
        return object : DisposableObserver<WordlistState>() {

            override fun onNext(appState: WordlistState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(WordlistState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}
