package com.dlgdev.cointracker

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.dlgdev.cointracker.db.CoinDatabase
import kotlinx.android.synthetic.main.activity_coin_list.*
import kotlinx.android.synthetic.main.content_coin_list.*

class CoinListActivity : AppCompatActivity() {

    lateinit var db: CoinDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ -> val intent = Intent(this, AddCoinActivity::class.java)
            startActivity(intent)
        }
        db = Room.databaseBuilder(this, CoinDatabase::class.java, "coins.db").build()

        coin_list_view.adapter = CoinAdapter()
        coin_list_view.layoutManager = LinearLayoutManager(this)
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
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
