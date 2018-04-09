package com.dlgdev.cointracker.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dlgdev.cointracker.R

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView (R.id.coin_item_amount) lateinit var amount: TextView
    @BindView(R.id.coin_item_currency) lateinit var currency: TextView
    @BindView(R.id.coin_item_description) lateinit var description: TextView
    @BindView(R.id.delete_coin) lateinit var delete: ImageView
    @BindView(R.id.edit_coin) lateinit var edit: ImageView
    init {
        ButterKnife.bind(this, itemView)
    }
}
