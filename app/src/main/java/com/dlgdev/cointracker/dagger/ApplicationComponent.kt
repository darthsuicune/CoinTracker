package com.dlgdev.cointracker.dagger

import com.dlgdev.cointracker.CoinApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [(AndroidSupportInjectionModule::class),
            (CoinListActivityModule::class),
            (CoinDbModule::class),
            (AddCoinActivityModule::class),
            (EditCoinActivityModule::class),
            (PicassoModule::class)])
interface ApplicationComponent : AndroidInjector<CoinApplication>