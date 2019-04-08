package me.golanshay.android.itunessearch.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import me.golanshay.android.itunessearch.R
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import java.util.ArrayList

class ItemsAdapter(var context: Context?) : RecyclerView.Adapter<ItemsViewHolder>() {

    private val itemClicks = PublishSubject.create<Int>()
    private val deleteClicks = PublishSubject.create<Int>()
    private var listItems: ArrayList<ITuneSearchResult> = ArrayList()

    fun swapAdapter(items: ArrayList<ITuneSearchResult>) {
        this.listItems.clear()
        this.listItems.addAll(items)
        notifyDataSetChanged()
    }

    fun observeClicks(): Observable<Int> {
        return itemClicks
    }

    fun observeDeleteClicks(): Observable<Int> {
        return deleteClicks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemsViewHolder(this, Picasso.get(), view, itemClicks, deleteClicks)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}