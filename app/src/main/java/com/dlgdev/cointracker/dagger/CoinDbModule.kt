package com.dlgdev.cointracker.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.dlgdev.cointracker.db.CoinDao
import com.dlgdev.cointracker.db.CoinDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoinDbModule(val context: Context) {

    @Singleton
    @Provides fun provideCoinDb(): CoinDatabase {
        return Room.databaseBuilder(context, CoinDatabase::class.java, "coins.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides fun provideCoinDao(db: CoinDatabase): CoinDao {
        return db.coinDao()
    }
}