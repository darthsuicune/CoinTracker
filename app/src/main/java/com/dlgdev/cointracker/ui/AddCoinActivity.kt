package com.dlgdev.cointracker.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dlgdev.cointracker.R
import com.dlgdev.cointracker.db.CoinDao
import com.dlgdev.cointracker.model.Coin
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_coin.*
import javax.inject.Inject

class AddCoinActivity : DaggerAppCompatActivity() {

    @Inject lateinit var coinDao: CoinDao
    @Inject lateinit var picasso: Picasso
    @Inject lateinit var pictureHelper: PictureHelper
    var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)
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

        photoUri = data!!.data
        pictureHelper.persistTheUriPermission(photoUri!!, contentResolver, data)
        showPicture()
    }

    private fun showPicture() {
        coin_picture.visibility = View.VISIBLE
        coin_placeholder.visibility = View.GONE
        picasso.load(photoUri).into(coin_picture)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add_coin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add_coin -> {
                addCoin(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addCoin() {
        val coin = Coin()
        coin.amount = coin_amount.selectedItem as String
        coin.currency = coin_currency.selectedItem as String
        coin.description = coin_description.text.toString()
        if (photoUri != null) coin.photoUri = photoUri!!.toString()

        AddCoinTask().execute(coin)
    }

    inner class AddCoinTask : AsyncTask<Coin, Unit, Unit>() {
        override fun doInBackground(vararg p0: Coin?) {
            coinDao.insertAll(p0[0]!!)
            Log.d("Insert", "Coin inserted successfully")
        }

        override fun onPostExecute(result: Unit?) {
            val message = "Coin added"
            Snackbar.make(coin_amount, message, Snackbar.LENGTH_LONG).show()
        }
    }
}
