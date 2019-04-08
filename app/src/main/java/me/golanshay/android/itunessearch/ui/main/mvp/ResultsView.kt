package me.golanshay.android.itunessearch.ui.main.mvp

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_items_list.view.*
import me.golanshay.android.itunessearch.R
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import me.golanshay.android.itunessearch.ui.main.ResultsFragment
import me.golanshay.android.itunessearch.ui.main.adapter.ItemsAdapter
import java.util.ArrayList

class ResultsView(var context: ResultsFragment?) {
    var view: View
    private var adapter: ItemsAdapter

    init {
        val parent = FrameLayout(context?.activity as Context)
        parent.layoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view = LayoutInflater.from(context?.activity).inflate(R.layout.activity_items_list, parent, true)

        adapter = ItemsAdapter(context?.activity as Context)

        view.rv_list?.adapter = adapter

        view.rv_list?.layoutManager =
            if (context?.activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT)
                LinearLayoutManager(context?.activity) else
                GridLayoutManager(context?.activity, 2)

        view.swipe_container.setOnRefreshListener {
            context?.presenter?.getItemList()
        }
    }

    fun finish() {
        context?.activity?.finish()
    }

    fun getString(resourceId: Int): String? {
        return context?.activity?.getString(resourceId)
    }

    fun itemClicks(): Observable<Int> {
        return adapter.observeClicks()
    }

    fun deleteClicks(): Observable<Int> {
        return adapter.observeDeleteClicks()
    }

    fun swapAdapter(items: ArrayList<ITuneSearchResult>) {
        adapter.swapAdapter(items)
    }

    fun setRefreshing(isRefreshing: Boolean) {
        context?.activity?.runOnUiThread { view.swipe_container.isRefreshing = isRefreshing }
    }

    fun showError(message: String?) {
        context?.activity?.runOnUiThread { Toast.makeText(context?.activity, message, Toast.LENGTH_SHORT).show() }
    }

    fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }
}
