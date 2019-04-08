package me.golanshay.android.itunessearch.ui.details.di

import dagger.Component
import me.golanshay.android.itunessearch.ui.details.DetailsActivity

@Component(modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(context: DetailsActivity)
}