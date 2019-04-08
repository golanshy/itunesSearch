package me.golanshay.android.itunessearch.ui.main.mvp

import androidx.lifecycle.ViewModel
import me.golanshay.android.itunessearch.model.ITuneSearchResult

class MainViewModel : ViewModel() {

    var results: ArrayList<ITuneSearchResult>? = ArrayList()
    val visitedMap: HashMap<Int, Boolean> = HashMap()
    val removedMap: HashMap<Int, Boolean> = HashMap()
}