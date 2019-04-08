package me.golanshay.android.itunessearch.di

import dagger.Module
import dagger.Provides
import me.golanshay.android.itunessearch.utils.AppRxSchedulers
import me.golanshay.android.itunessearch.utils.RxSchedulers

@Module
class RxModule {
    @Provides
    internal fun provideRxSchedulers(): RxSchedulers {
        return AppRxSchedulers()
    }
}