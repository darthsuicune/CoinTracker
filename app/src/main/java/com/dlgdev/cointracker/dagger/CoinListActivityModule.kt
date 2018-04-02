package com.dlgdev.cointracker.dagger

import com.dlgdev.cointracker.CoinListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoinListActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeActivity() : CoinListActivity

}