package com.dlgdev.cointracker

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView (R.id.coin_item_amount) lateinit var amount: TextView
    @BindView(R.id.coin_item_currency) lateinit var currency: TextView
    @BindView(R.id.coin_item_description) lateinit var description: TextView
    @BindView(R.id.coin_item_delete) lateinit var delete: ImageView
    init {
        ButterKnife.bind(this, itemView)
    }
}
