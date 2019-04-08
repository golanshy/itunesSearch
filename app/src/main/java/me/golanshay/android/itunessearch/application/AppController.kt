package me.golanshay.android.itunessearch.application

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import timber.log.BuildConfig
import timber.log.Timber

class AppController : Application() {

    private var scheduler: Scheduler? = null //Rx object

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var mInstance: AppController

        fun getNetComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initialiseLogger()
        initAppComponent()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

    }

    @Synchronized
    fun getInstance(): AppController {
        return mInstance
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }
        return scheduler
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(
            AppContextModule(
                this
            )
        ).build()
    }


    private fun initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String, message: String, t: Throwable) {
                    Log.d("Logger", message)
                }
            })
        }
    }
}