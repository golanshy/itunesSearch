package me.golanshay.android.itunessearch.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(var context: Context) {

    @AppScope
    @Provides
    internal fun provideAppContext(): Context {
        return context
    }
}