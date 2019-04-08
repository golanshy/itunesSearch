package me.golanshay.android.itunessearch.ui.main.adapter

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import kotlinx.android.synthetic.main.layout_item.view.*
import me.golanshay.android.itunessearch.R

class ItemsViewHolder(private val adapter: ItemsAdapter, private val picasso: Picasso, private val view: View, private val clickSubject: PublishSubject<Int>, private val deleteSubject: PublishSubject<Int>) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener { clickSubject.onNext(adapterPosition) }
    }

    internal fun bind(item: ITuneSearchResult) {
        val textColor = if (item.visited) Color.CYAN else Color.WHITE
        view.item_track!!.setTextColor(textColor)
        view.item_artist!!.setTextColor(textColor)
        view.item_collection!!.setTextColor(textColor)

        view.item_track!!.text = if (TextUtils.isEmpty(item.trackCensoredName)) adapter.context?.getString(R.string.missing_track_name) else item.trackCensoredName
        view.item_artist!!.text = if (TextUtils.isEmpty(item.artistName)) adapter.context?.getString(R.string.missing_artist_name) else item.artistName
        view.item_collection!!.text = if (TextUtils.isEmpty(item.collectionCensoredName)) adapter.context?.getString(R.string.missing_collection_name) else item.collectionCensoredName

        picasso.load(item.artworkUrl60).into(view.item_image)
        view.btn_delete.setOnClickListener { deleteSubject.onNext(adapterPosition) }
    }
}