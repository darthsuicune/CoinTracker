package com.dlgdev.cointracker

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_coin_list.*
import javax.inject.Inject

class CoinListActivity : DaggerAppCompatActivity() {
    @Inject lateinit var coinDao: CoinDao
    @Inject lateinit var adapter: CoinAdapter
    @BindView(R.id.coin_list_view) lateinit var coinView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        fab.setOnClickListener { _ ->
            val intent = Intent(this, AddCoinActivity::class.java)
            startActivity(intent)
        }
        coinView.adapter = adapter
        coinView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        coinDao.allCoins().observe(this,
                Observer<List<Coin>> { t -> adapter.showCoins(t!!) })    }

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
