package me.golanshay.android.itunessearch.utils

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable
import io.reactivex.Observable.just

class NetworkUtils {

    companion object {
        private fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun isNetworkAvailableObservable(context: Context): Observable<Boolean> {
            return just(NetworkUtils.isNetworkAvailable(context))
        }
    }
}