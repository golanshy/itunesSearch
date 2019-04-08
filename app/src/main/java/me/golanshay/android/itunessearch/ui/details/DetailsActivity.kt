package me.golanshay.android.itunessearch.ui.details

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.golanshay.android.itunessearch.model.ITuneSearchResult
import me.golanshay.android.itunessearch.ui.details.di.DaggerDetailsComponent
import me.golanshay.android.itunessearch.ui.details.di.DetailsModule
import javax.inject.Inject
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import me.golanshay.android.itunessearch.R
import androidx.lifecycle.ViewModelProviders
import me.golanshay.android.itunessearch.ui.main.mvp.MainViewModel

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var view: DetailsView

    private lateinit var viewModel: MainViewModel
    private var item: ITuneSearchResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = this.intent?.extras?.get("serialized_search_item") as ITuneSearchResult
        DaggerDetailsComponent.builder().detailsModule(DetailsModule(this, item)).build().inject(this)
        setContentView(view.view)

        viewModel = run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_delete, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_delete -> {
                if (item?.trackId != null) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.delete_confirmation_title))
                    builder.setMessage(getString(R.string.delete_confirmation_body))
                    builder.setPositiveButton(getString(R.string.yes)) { _, _ -> run {
                        val returnIntent = Intent()
                        returnIntent.putExtra("result", item?.trackId!!)
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    } }
                    builder.setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    return true
                } else {
                    Toast.makeText(this, getString(R.string.delete_failed), Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}