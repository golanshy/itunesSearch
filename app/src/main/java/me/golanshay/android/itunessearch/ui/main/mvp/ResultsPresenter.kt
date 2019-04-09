package me.golanshay.android.itunessearch.ui.main.mvp

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.golanshay.android.itunessearch.R
import me.golanshay.android.itunessearch.utils.RxSchedulers
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import java.util.ArrayList

class ResultsPresenter(
    private var rxSchedulers: RxSchedulers,
    val model: ResultsModel,
    val view: ResultsView,
    private val subscriptions: CompositeDisposable
) {

    fun onCreate() {
        subscriptions.add(getItemList())
        subscriptions.add(respondToClick())
        subscriptions.add(respondToDelete())
    }

    fun onDestroy() {
        subscriptions.clear()
    }

    fun getItemList(): Disposable {
        view.setRefreshing(isRefreshing = true)
        return model.isNetworkAvailable().doOnNext { networkAvailable ->
            if (!networkAvailable) {
                view.showError(view.getString(R.string.no_internet))
            }
        }.filter { true }.flatMap { model.provideListResults() }
            .subscribeOn(rxSchedulers.internet()).observeOn(rxSchedulers.androidThread()).subscribe(
                { items ->
                    processResults(items.results)
                    view.setRefreshing(isRefreshing = false)
                }, { throwable ->
                    run {
                        view.setRefreshing(isRefreshing = false)
                        view.showError(throwable.message)
                    }
                }
            )
    }

    private fun respondToClick(): Disposable {
        return view.itemClicks().subscribe { integer ->
            run {
                if (view.context?.viewModel?.results!![integer.toInt()].trackId != null)
                    view.context?.viewModel?.visitedMap?.put(
                        view.context?.viewModel?.results!![integer.toInt()].trackId!!,
                        true
                    )
                view.context?.viewModel?.results!![integer.toInt()].visited = true
                view.updateAdapter()
                model.goToDetailsActivity(view.context?.viewModel?.results!![integer.toInt()])
            }
        }
    }

    private fun respondToDelete(): Disposable {
        return view.deleteClicks().subscribe { integer ->
            run {
                if (view.context?.viewModel?.results!![integer.toInt()].trackId != null) {
                    val builder = AlertDialog.Builder(view.context?.activity!!)
                    builder.setTitle(view.context?.activity?.getString(R.string.delete_confirmation_title))
                    builder.setMessage(view.context?.activity?.getString(R.string.delete_confirmation_body))
                    builder.setPositiveButton(view.context?.activity?.getString(R.string.yes)) { _, _ -> run {
                        view.context?.viewModel?.removedMap?.put(
                            view.context?.viewModel?.results!![integer.toInt()].trackId!!,
                            true
                        )
                        processResults(view.context?.viewModel?.results)
                        Toast.makeText(
                            view.context?.activity,
                            view.context?.activity?.getString(R.string.deleted_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    } }
                    builder.setNegativeButton(view.context?.activity?.getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {
                    Toast.makeText(
                        view.context?.activity,
                        view.context?.getString(R.string.delete_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    internal fun processResults(results: List<ITuneSearchResult>?) {
        view.context?.viewModel?.results = ArrayList()
        results?.forEach {
            it.visited =
                view.context?.viewModel?.visitedMap?.size != null && view.context?.viewModel?.visitedMap?.size!! > 0 && it.trackId != null && view.context?.viewModel?.visitedMap?.contains(
                    it.trackId
                )!!
            if (!(view.context?.viewModel?.removedMap?.size != null && view.context?.viewModel?.removedMap?.size!! > 0 && it.trackId != null && view.context?.viewModel?.removedMap?.contains(
                    it.trackId
                )!!)
            ) {
                view.context?.viewModel?.results!!.add(it)
            }
        }
        view.swapAdapter(view.context?.viewModel?.results!!)
    }
}