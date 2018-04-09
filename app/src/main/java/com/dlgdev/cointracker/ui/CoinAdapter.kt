package com.dlgdev.cointracker.ui

import android.content.Intent
import android.os.AsyncTask
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dlgdev.cointracker.R
import com.dlgdev.cointracker.db.CoinDao
import com.dlgdev.cointracker.model.Coin
import com.dlgdev.cointracker.ui.EditCoinActivity.Companion.KEY_ID
import com.squareup.picasso.Picasso
import javax.inject.Inject


class CoinAdapter @Inject constructor(val activity: CoinListActivity, val coinDao: CoinDao,
                                      val picasso: Picasso) :
        RecyclerView.Adapter<CoinViewHolder>() {
    var coins: List<Coin> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CoinViewHolder {
        return CoinViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.coin_item, parent,
                        false))
    }

    override fun onBindViewHolder(viewHolder: CoinViewHolder, position: Int) {
        val coin = coins[position]

        viewHolder.amount.text = coin.amount
        viewHolder.currency.text = coin.currency
        viewHolder.description.text = coin.description
        viewHolder.edit.setOnClickListener { editCoin(coin) }
        viewHolder.delete.setOnClickListener {
            AlertDialog.Builder(viewHolder.amount.context)
                    .setTitle(R.string.delete_coin_dialog_title)
                    .setMessage(R.string.delete_coin_dialog_text)
                    .setPositiveButton(R.string.ok, { _, _ ->
                        DeleteCoinTask().execute(coin)
                    })
                    .setNegativeButton(R.string.no, { _, _ ->
                        //Do nothing
                    })
                    .create()
                    .show()
        }
    }

    private fun editCoin(coin: Coin) {
        val intent = Intent(activity, EditCoinActivity::class.java)
        intent.putExtra(KEY_ID, coin.id)
        activity.startActivity(intent)

    }

    override fun getItemCount(): Int {
        return coins.size
    }

    inner class DeleteCoinTask : AsyncTask<Coin, Unit, Unit>() {
        override fun doInBackground(vararg p0: Coin) {
            coinDao.delete(p0[0])
            Log.d("Delete", "Coin removed successfully")
        }

        override fun onPostExecute(result: Unit?) {
            val message = "Coin removed"
            Snackbar.make(activity.findViewById(R.id.coin_list_view), message, Snackbar.LENGTH_LONG)
                    .show()
        }
    }
}

