package com.dlgdev.cointracker.ui

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import com.dlgdev.cointracker.R
import com.dlgdev.cointracker.db.CoinDao
import com.dlgdev.cointracker.model.Coin
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_coin.*
import javax.inject.Inject

class EditCoinActivity : DaggerAppCompatActivity() {
    companion object {
        const val KEY_ID = "id"
    }

    @Inject lateinit var coinDao: CoinDao
    @Inject lateinit var picasso: Picasso
    @Inject lateinit var pictureHelper: PictureHelper

    lateinit var coin: LiveData<Coin>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (intent.extras == null || !intent.extras.containsKey(KEY_ID)) {
            throw RuntimeException("FUCK YOU")
        }
        setContentView(R.layout.activity_add_coin)
        loadCoin(intent.extras.getInt(KEY_ID))
        coin_placeholder.setOnClickListener { pictureHelper.requestGallery(this) }
        coin_picture.setOnClickListener { pictureHelper.requestGallery(this) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        pictureHelper.checkPermissionResults(this, requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        coin.value!!.photoUri = data!!.data!!.toString()
        pictureHelper.persistTheUriPermission(data.data, contentResolver, data)
        EditCoinTask().execute()
    }

    private fun loadCoin(id: Int) {
        coin = coinDao.coinWithId(id)
        coin.observe(this, Observer {
            if (it != null) showCoinData(it)
        })
    }

    private fun showCoinData(coin: Coin) {
        findAndSetSelection(coin.amount, coin_amount)
        findAndSetSelection(coin.currency, coin_currency)
        coin_description.setText(coin.description)
        if(coin.photoUri != null) {
            showPicture(coin.photoUri!!)
        }
    }

    private fun showPicture(photoUri: String) {
        coin_picture.visibility = View.VISIBLE
        coin_placeholder.visibility = View.GONE
        picasso.load(photoUri).into(coin_picture)
    }

    private fun findAndSetSelection(value: String, spinner: Spinner) {
        for (i in 0 until spinner.adapter.count) {
            if ((spinner.adapter.getItem(i) as String) == value) {
                spinner.setSelection(i)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit_coin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.apply_edit -> {
                editCoin()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun editCoin() {
        coin.value!!.amount = coin_amount.selectedItem as String
        coin.value!!.currency = coin_currency.selectedItem as String
        coin.value!!.description = coin_description.text.toString()

        EditCoinTask().execute()
    }

    inner class EditCoinTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            coinDao.updateCoins(coin.value!!)
            Log.d("Update", "Coin updated successfully")
        }

        override fun onPostExecute(result: Unit?) {
            val message = "Coin updated"
            Snackbar.make(coin_amount, message, Snackbar.LENGTH_LONG).show()
        }
    }
}