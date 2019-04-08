package me.golanshay.android.itunessearch.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import me.golanshay.android.itunessearch.utils.AppRxSchedulers
import me.golanshay.android.itunessearch.application.AppScope
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module
class NetworkModule {
    @AppScope
    @Provides
    fun provideHttpClient(logger: HttpLoggingInterceptor, cache: Cache): OkHttpClient {

        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(logger)
        builder.cache(cache)
        return builder.build()
    }

    @AppScope
    @Provides
    internal fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @AppScope
    @Provides
    internal fun provideCache(file: File): Cache {
        return Cache(file, (10 * 10 * 1000).toLong())
    }

    @AppScope
    @Provides
    internal fun provideCacheFile(context: Context): File {
        return context.filesDir
    }

    @AppScope
    @Provides
    internal fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS)
    }


    @Provides
    internal fun provideGsonClient(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}