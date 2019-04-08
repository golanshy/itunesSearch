package me.golanshay.android.itunessearch.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import me.golanshay.android.itunessearch.R
import me.golanshay.android.itunessearch.utils.UiUtils
import me.golanshay.android.itunessearch.ui.main.mvp.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var resultFragment: ResultsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(me.golanshay.android.itunessearch.R.layout.activity_main)

        viewModel = run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

        resultFragment = ResultsFragment()
        UiUtils.launchWithoutBackStack(this,resultFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1000 -> {
                if (resultCode == Activity.RESULT_OK) {
                    val trackId: Int? = data?.getIntExtra("result", 0)
                    if (trackId != null && trackId > 0) {
                        resultFragment.removeItem(trackId)
                        Toast.makeText(this, getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
