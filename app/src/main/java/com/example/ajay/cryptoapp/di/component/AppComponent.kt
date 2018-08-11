package com.example.ajay.cryptoapp.di.component

import com.example.ajay.cryptoapp.CryptocurrencyApplication
import com.example.ajay.cryptoapp.di.module.AppModule
import com.example.ajay.cryptoapp.di.module.BuildersModule
import com.example.ajay.cryptoapp.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule


@Component(modules = arrayOf(AndroidInjectionModule::class,
        AppModule::class,
        BuildersModule::class,
        NetModule::class))
interface AppComponent {

    fun inject(app: CryptocurrencyApplication)
}