package com.dlgdev.cointracker.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.dlgdev.cointracker.model.Coin

@Dao
interface CoinDao {
    @Query("SELECT * FROM coins")
    fun allCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM coins WHERE currency=:currency")
    fun allCoinsForCurrency(currency: String): LiveData<List<Coin>>

    @Query("SELECT * FROM coins WHERE id=:id LIMIT 1")
    fun coinWithId(id: Int): LiveData<Coin>

    @Insert fun insertAll(vararg coins: Coin)
    @Update fun updateCoins(vararg coins: Coin)
    @Delete fun delete(coin: Coin)

}