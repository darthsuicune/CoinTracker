package com.dlgdev.cointracker.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.dlgdev.cointracker.R
import com.dlgdev.cointracker.db.CoinDao
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_coin_list.*
import kotlinx.android.synthetic.main.content_coin_list.*
import javax.inject.Inject

class CoinListActivity : DaggerAppCompatActivity() {
    @Inject lateinit var coinDao: CoinDao
    @Inject lateinit var adapter: CoinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            val intent = Intent(this, AddCoinActivity::class.java)
            startActivity(intent)
        }
        coinDao.allCoins().observe(this, Observer { t ->
                if (t != null) adapter.coins = t
        })

        coin_list_view.adapter = adapter
        coin_list_view.layoutManager = LinearLayoutManager(this)
        coin_list_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_coin_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
