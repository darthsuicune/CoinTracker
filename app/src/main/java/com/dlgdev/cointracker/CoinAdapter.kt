package com.dlgdev.cointracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CoinAdapter : RecyclerView.Adapter<CoinViewHolder>() {
    var coins: List<Coin> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CoinViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        val vh = CoinViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(viewHolder: CoinViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return coins.size
    }
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}