package me.golanshay.android.itunessearch.ui.details.di

import dagger.Module
import dagger.Provides
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import me.golanshay.android.itunessearch.ui.details.DetailsActivity
import me.golanshay.android.itunessearch.ui.details.DetailsView

@Module
class DetailsModule(private var detailsContext: DetailsActivity, private var item: ITuneSearchResult?) {

    @Provides
    internal fun provideView(): DetailsView {
        return DetailsView(detailsContext, item)
    }
}