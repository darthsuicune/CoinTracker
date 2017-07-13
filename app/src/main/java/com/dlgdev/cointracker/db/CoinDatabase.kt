package com.dlgdev.cointracker.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dlgdev.cointracker.Coin
import com.dlgdev.cointracker.CoinDao

@Database(entities = arrayOf(Coin::class), version = 1)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}