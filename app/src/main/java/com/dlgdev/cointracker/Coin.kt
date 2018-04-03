package com.dlgdev.cointracker

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity (tableName = "coins")
class Coin {
    @PrimaryKey(autoGenerate = true)var id = 0
    var amount = "2"
    var currency = "€"
    var description: String? = null
    var photoUri: String? = null
}

