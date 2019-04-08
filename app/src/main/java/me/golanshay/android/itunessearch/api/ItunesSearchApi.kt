package me.golanshay.android.itunessearch.api

import io.reactivex.Observable
import me.golanshay.android.itunessearch.model.ITuneSearchResults
import retrofit2.http.GET

interface ItunesSearchApi {
    @GET("search?term=80s&limit=100&country=GB")
    fun doSearch(): Observable<ITuneSearchResults>
}