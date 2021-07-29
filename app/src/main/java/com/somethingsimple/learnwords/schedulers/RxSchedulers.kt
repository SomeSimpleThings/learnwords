package com.somethingsimple.learnwords.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface RxSchedulers {

    fun ui(): Scheduler

    fun io(): Scheduler
}