package com.dlgdev.cointracker

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import javax.inject.Inject


class CoinAdapter @Inject constructor(val coinDao: CoinDao) :
        RecyclerView.Adapter<CoinViewHolder>() {
    var coins: List<Coin> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CoinViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CoinViewHolder(v, object: DeleteCoinCallback {
            override fun delete(coin: Coin) {
                DeleteCoinTask().execute(coin)
            }
        })
    }

    override fun onBindViewHolder(viewHolder: CoinViewHolder, position: Int) {
        viewHolder.coin = coins[position]
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    fun showCoins(newCoinList: List<Coin>) {
        coins = newCoinList
        this.notifyDataSetChanged()
    }

    inner class DeleteCoinTask : AsyncTask<Coin, Unit, Unit>() {
        override fun doInBackground(vararg p0: Coin) {
            coinDao.delete(p0[0])
            Log.d("Insert", "Coin removed successfully")
        }

        override fun onPostExecute(result: Unit) {
            notifyDataSetChanged()
        }
    }
}

