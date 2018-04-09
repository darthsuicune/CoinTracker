package com.dlgdev.cointracker.dagger

import com.dlgdev.cointracker.ui.EditCoinActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EditCoinActivityModule {
    @ContributesAndroidInjector
    abstract fun provideActivity(): EditCoinActivity
}