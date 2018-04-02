package com.dlgdev.cointracker

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Entity (tableName = "coins")
class Coin {
    @PrimaryKey(autoGenerate = true)var id = 0
    var amount = "2"
    var currency = "€"
    var description: String? = null
    var photoUri: String? = null
}

@Dao
interface CoinDao {
    @Query("SELECT * FROM coins")
    fun allCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM coins WHERE currency=:currency")
    fun allCoinsForCurrency(currency: String): LiveData<List<Coin>>

    @Insert fun insertAll(vararg coins: Coin)
    @Update fun updateCoins(vararg coins: Coin)
    @Delete fun delete(coin: Coin)

}