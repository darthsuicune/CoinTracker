package com.dlgdev.cointracker.dagger

import com.dlgdev.cointracker.CoinApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [(AndroidSupportInjectionModule::class), (CoinListActivityModule::class), (CoinDbModule::class), (AddCoinActivityModule::class)])
interface ApplicationComponent : AndroidInjector<CoinApplication>