package me.golanshay.android.itunessearch.ui.details

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.squareup.picasso.Picasso
import me.golanshay.android.itunessearch.R
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import kotlinx.android.synthetic.main.activity_item_details.view.*

class DetailsView(
    private var detailsContext: DetailsActivity,
    var item: ITuneSearchResult?
) {

    internal var view: View

    init {
        detailsContext.title =
            if (TextUtils.isEmpty(item?.trackCensoredName)) detailsContext.getString(R.string.missing_track_name) else "${item?.trackCensoredName}"
        val layout = FrameLayout(detailsContext)
        layout.layoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view = LayoutInflater.from(detailsContext).inflate(R.layout.activity_item_details, layout, true)
        Picasso.get().load(item?.getLargestImage()).into(view.item_image)

        view.item_track!!.text =
            if (TextUtils.isEmpty(item?.trackCensoredName)) detailsContext.getString(R.string.missing_track_name) else "${detailsContext.getString(
                R.string.track
            )}: ${item?.trackCensoredName}"
        view.item_artist!!.text =
            if (TextUtils.isEmpty(item?.artistName)) detailsContext.getString(R.string.missing_artist_name) else "${detailsContext.getString(
                R.string.artist
            )}: ${item?.artistName}"
        view.item_collection!!.text =
            if (TextUtils.isEmpty(item?.collectionCensoredName)) detailsContext.getString(R.string.missing_collection_name) else "${detailsContext.getString(
                R.string.collection
            )}: ${item?.collectionCensoredName}"
    }
}