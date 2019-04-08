package me.golanshay.android.itunessearch.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.golanshay.android.itunessearch.R

class UiUtils {

    companion object {

        fun launchWithBackStack(context: Context, fragmentToLaunch: Fragment) {
            launchWithBackStack(context, fragmentToLaunch, fragmentToLaunch.javaClass.canonicalName!!)
        }

        fun launchWithoutBackStack(context: Context, fragmentToLaunch: Fragment) {
            launchWithoutBackStack(context, fragmentToLaunch, fragmentToLaunch.javaClass.canonicalName!!)
        }

        private fun launchWithBackStack(
            context: Context,
            fragmentToLaunch: Fragment,
            fragmentTag: String
        ) {
            val supportFragmentManager = (context as AppCompatActivity).supportFragmentManager
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragmentToLaunch, fragmentTag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        private fun launchWithoutBackStack(
            context: Context,
            fragmentToLaunch: Fragment,
            fragmentTag: String
        ) {
            val supportFragmentManager = (context as AppCompatActivity).supportFragmentManager
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragmentToLaunch, fragmentTag)
                .commitAllowingStateLoss()
        }
    }
}