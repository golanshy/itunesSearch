package me.golanshay.android.itunessearch.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import me.golanshay.android.itunessearch.api.ItunesSearchApi
import me.golanshay.android.itunessearch.application.AppScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ITunesSearchApiServiceModule {
    private val baseURL = "https://itunes.apple.com/"
    @AppScope
    @Provides
    internal fun provideApiService(
        client: OkHttpClient,
        gson: GsonConverterFactory,
        rxAdapter: RxJava2CallAdapterFactory
    ): ItunesSearchApi {
        val retrofit = Retrofit.Builder().client(client)
            .baseUrl(baseURL).addConverterFactory(gson).addCallAdapterFactory(rxAdapter).build()
        return retrofit.create<ItunesSearchApi>(ItunesSearchApi::class.java)
    }
}