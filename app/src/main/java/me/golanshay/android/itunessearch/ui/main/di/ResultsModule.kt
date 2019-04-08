package me.golanshay.android.itunessearch.ui.main.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.golanshay.android.itunessearch.utils.RxSchedulers
import me.golanshay.android.itunessearch.api.ItunesSearchApi
import me.golanshay.android.itunessearch.ui.main.ResultsFragment
import me.golanshay.android.itunessearch.ui.main.mvp.ResultsModel
import me.golanshay.android.itunessearch.ui.main.mvp.ResultsPresenter
import me.golanshay.android.itunessearch.ui.main.mvp.ResultsView

@Module
class ResultsModule(private val resultsListContext: ResultsFragment) {

    @ResultsScope
    @Provides
    internal fun provideView(): ResultsView {
        return ResultsView(resultsListContext)
    }

    @ResultsScope
    @Provides
    internal fun providePresenter(schedulers: RxSchedulers, view: ResultsView, model: ResultsModel): ResultsPresenter {
        val subscriptions = CompositeDisposable()
        return ResultsPresenter(schedulers, model, view, subscriptions)
    }

    @ResultsScope
    @Provides
    internal fun provideContext(): ResultsFragment? {
        return resultsListContext
    }

    @ResultsScope
    @Provides
    internal fun provideModel(api: ItunesSearchApi): ResultsModel {
        return ResultsModel(resultsListContext, api)
    }
}