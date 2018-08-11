package com.example.ajay.cryptoapp.di.module

import com.example.ajay.cryptoapp.data.Cryptocurrency
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptocurrenciesActivity(): Cryptocurrency
}