package me.golanshay.android.itunessearch.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.reactivex.annotations.NonNull
import me.golanshay.android.itunessearch.application.AppController
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import me.golanshay.android.itunessearch.ui.details.DetailsActivity
import me.golanshay.android.itunessearch.ui.main.di.DaggerResultsComponent
import me.golanshay.android.itunessearch.ui.main.di.ResultsModule
import me.golanshay.android.itunessearch.ui.main.mvp.MainViewModel
import me.golanshay.android.itunessearch.ui.main.mvp.ResultsPresenter
import me.golanshay.android.itunessearch.ui.main.mvp.ResultsView
import javax.inject.Inject
import java.io.Serializable

class ResultsFragment : Fragment() {

    @Inject
    lateinit var view: ResultsView

    @Inject
    lateinit var presenter: ResultsPresenter

    internal lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        DaggerResultsComponent.builder().appComponent(AppController.getNetComponent())
            .resultsModule(ResultsModule(this))
            .build().inject(this)
        presenter.onCreate()
    }

    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return view.view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun goToDetailsActivity(item: ITuneSearchResult) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("serialized_search_item", item as Serializable)
        activity?.startActivityForResult(intent, 1000)
    }

    fun removeItem(trackId: Int) {
        viewModel.removedMap[trackId] = true
        presenter.processResults(viewModel.results)
    }
}