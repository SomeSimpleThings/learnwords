package com.somethingsimple.learnwords.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.schedulers.RxSchedulers
import com.somethingsimple.learnwords.schedulers.RxSchedulersImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : WordlistState>(
    protected open val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected open val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected open val schedulers: RxSchedulers = RxSchedulersImpl
) : ViewModel() {

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
