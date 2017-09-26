package com.dlgdev.cointracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_coin.*

class AddCoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coin)
        currency_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayOf("€"))
        country_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayOf("Germany", "Spain", "Italy", "France"))
    }
}
