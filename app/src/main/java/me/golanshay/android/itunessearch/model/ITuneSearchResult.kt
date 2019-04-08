package me.golanshay.android.itunessearch.model

import com.google.gson.annotations.Expose
import java.io.Serializable

class ITuneSearchResult : Serializable {
    @Expose
    var trackId: Int? = null
    @Expose
    var trackCensoredName: String? = null
    @Expose
    var artistName: String? = null
    @Expose
    var collectionCensoredName: String? = null
    @Expose
    var artworkUrl30: String? = null
    @Expose
    var artworkUrl60: String? = null
    @Expose
    var artworkUrl100: String? = null
    @Expose
    var artworkUrl600: String? = null
    @Expose
    var previewUrl: String? = null

    var visited: Boolean = false

    fun getLargestImage(): String? {
        return if (!this.artworkUrl600.isNullOrEmpty()) {
            artworkUrl600
        } else if (!this.artworkUrl100.isNullOrEmpty()) {
            artworkUrl100
        } else if (!this.artworkUrl100.isNullOrEmpty()) {
            artworkUrl100
        } else if (!this.artworkUrl60.isNullOrEmpty()) {
            artworkUrl60
        } else if (!this.artworkUrl30.isNullOrEmpty()) {
            artworkUrl30
        } else null
    }
}