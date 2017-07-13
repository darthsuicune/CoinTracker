package com.dlgdev.cointracker

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dlgdev.cointracker.db.CoinDatabase

class AddCoinActivity : AppCompatActivity() {

    lateinit var db: CoinDatabase
    lateinit var coinView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)
        db = Room.databaseBuilder(this, CoinDatabase::class.java, "coins.db").build()

        coinView.adapter = CoinAdapter()
        coinView.layoutManager = LinearLayoutManager(this)
    }
}
