package me.golanshay.android.itunessearch.ui.main.di

import dagger.Component
import me.golanshay.android.itunessearch.application.AppComponent
import me.golanshay.android.itunessearch.ui.main.ResultsFragment

@ResultsScope
@Component(dependencies = [AppComponent::class], modules = [ResultsModule::class])
interface ResultsComponent {
    fun inject(fragment: ResultsFragment)
}