package me.golanshay.android.itunessearch.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppRxSchedulers : RxSchedulers {

    companion object {
        private var backgroundExecutor: Executor = Executors.newCachedThreadPool()
        private var internetExecutor: Executor = Executors.newCachedThreadPool()
        var BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor)
        var INTERNET_SCHEDULERS = Schedulers.from(internetExecutor)
    }

    override fun runOnBackground(): Scheduler {
        return BACKGROUND_SCHEDULERS
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun compute(): Scheduler {
        return Schedulers.computation()
    }

    override fun androidThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun internet(): Scheduler {
        return INTERNET_SCHEDULERS
    }
}