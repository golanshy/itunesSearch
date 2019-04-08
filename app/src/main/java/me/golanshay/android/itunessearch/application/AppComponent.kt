package me.golanshay.android.itunessearch.application

import dagger.Component
import me.golanshay.android.itunessearch.utils.RxSchedulers
import me.golanshay.android.itunessearch.api.ItunesSearchApi
import me.golanshay.android.itunessearch.di.*

@AppScope
@Component(modules = [NetworkModule::class, AppContextModule::class, RxModule::class, ITunesSearchApiServiceModule::class])
interface AppComponent {
    fun rxSchedulers(): RxSchedulers
    fun apiService(): ItunesSearchApi
}