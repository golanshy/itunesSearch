package me.golanshay.android.itunessearch.ui.main.mvp

import android.content.Context

import io.reactivex.Observable
import me.golanshay.android.itunessearch.utils.NetworkUtils
import me.golanshay.android.itunessearch.api.ItunesSearchApi
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import me.golanshay.android.itunessearch.model.ITuneSearchResults
import me.golanshay.android.itunessearch.ui.main.ResultsFragment


class ResultsModel(val context: ResultsFragment?, private val api: ItunesSearchApi) {

    internal fun provideListResults(): Observable<ITuneSearchResults> {
        return api.doSearch()
    }

    internal fun isNetworkAvailable(): Observable<Boolean> {
        return NetworkUtils.isNetworkAvailableObservable(context?.activity as Context)
    }

    fun goToDetailsActivity(item: ITuneSearchResult) {
        context?.goToDetailsActivity(item)
    }
}