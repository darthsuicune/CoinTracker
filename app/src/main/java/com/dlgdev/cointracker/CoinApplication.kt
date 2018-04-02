package com.dlgdev.cointracker

import com.dlgdev.cointracker.dagger.CoinDbModule
import com.dlgdev.cointracker.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CoinApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<CoinApplication> {
        return DaggerApplicationComponent.builder()
                .coinDbModule(CoinDbModule(this))
                .build()
    }
}