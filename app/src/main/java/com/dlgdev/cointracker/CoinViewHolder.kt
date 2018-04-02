package com.dlgdev.cointracker

import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class CoinViewHolder(itemView: View, var deleteCallback: DeleteCoinCallback) : RecyclerView.ViewHolder(itemView) {
    @BindView (R.id.coin_item_amount) lateinit var amount: TextView
    @BindView(R.id.coin_item_currency) lateinit var currency: TextView
    @BindView(R.id.coin_item_description) lateinit var description: TextView
    @BindView(R.id.coin_item_delete) lateinit var delete: ImageView
    init {
        ButterKnife.bind(this, itemView)
    }
    var coin: Coin = Coin()
    set(value) { field = value
        amount.text = value.amount
        currency.text = value.currency
        description.text = value.description
        delete.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                    .setTitle(R.string.delete_coin_dialog_title)
                    .setMessage(R.string.delete_coin_dialog_text)
                    .setPositiveButton(R.string.ok, { _, _ ->
                        deleteCallback.delete(coin)
                    })
                    .setNegativeButton(R.string.no, { _, _ ->
                        //Do nothing
                    })
                    .create()
                    .show()
        }
    }
}

interface DeleteCoinCallback {
    fun delete(coin: Coin)
}
