package com.dlgdev.cointracker.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.dlgdev.cointracker.db.CoinDao
import com.dlgdev.cointracker.db.CoinDatabase
import dagger.Module
import dagger.Provides

@Module
class CoinDbModule(val context: Context) {

    @Provides
    fun provideCoinDb(): CoinDao {
        return Room.databaseBuilder(context, CoinDatabase::class.java, "coins.db")
                .fallbackToDestructiveMigration()
                .build().coinDao()
    }
}