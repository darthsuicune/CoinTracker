package com.dlgdev.cointracker.dagger

import com.dlgdev.cointracker.ui.AddCoinActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddCoinActivityModule {
    @ContributesAndroidInjector
    abstract fun provideAddCoinActivity(): AddCoinActivity
}