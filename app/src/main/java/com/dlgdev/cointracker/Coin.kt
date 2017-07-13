package com.dlgdev.cointracker

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Entity
class Coin {
    @PrimaryKey var id = 0
    var amount = 0
    var currency = "€"
    var comment: String? = null
    var photoUri: String? = null
}

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin")
    fun allCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM coin WHERE currency=:currency")
    fun allCoinsForCurrency(currency: String): LiveData<List<Coin>>

    @Insert fun insertAll(vararg coins: Coin)
    @Update fun updateCoins(vararg coins: Coin)
    @Delete fun delete(coin: Coin)

}