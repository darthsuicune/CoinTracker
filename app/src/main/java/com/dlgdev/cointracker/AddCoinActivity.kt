package com.dlgdev.cointracker

import android.database.sqlite.SQLiteConstraintException
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import com.dlgdev.cointracker.db.CoinDao
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AddCoinActivity : DaggerAppCompatActivity() {

    @Inject lateinit var coinDao: CoinDao

    @BindView(R.id.coin_amount) lateinit var coinAmountView: Spinner
    @BindView(R.id.coin_currency) lateinit var coinCurrencyView: Spinner
    @BindView(R.id.coin_description) lateinit var coinDescriptionView: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)
        ButterKnife.bind(this)
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
        coin.amount = coinAmountView.selectedItem as String
        coin.currency = coinCurrencyView.selectedItem as String
        coin.description = coinDescriptionView.text.toString()

        AddCoinTask().execute(coin)
    }

    inner class AddCoinTask : AsyncTask<Coin, Unit, Unit>() {
        override fun doInBackground(vararg p0: Coin?) {
            try {
                coinDao.insertAll(p0[0]!!)
                Log.d("Insert", "Coin inserted successfully")
            } catch (sqle: SQLiteConstraintException) {
                Log.e("SQL Exception", sqle.message)
            }
        }

        override fun onPostExecute(result: Unit?) {
            Snackbar.make(coinAmountView, "Coin added", Snackbar.LENGTH_LONG).show()
        }
    }
}
