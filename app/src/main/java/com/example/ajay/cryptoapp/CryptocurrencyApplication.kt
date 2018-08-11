package com.example.ajay.cryptoapp

import android.app.Activity
import android.app.Application
import com.example.ajay.cryptoapp.di.module.AppModule
import com.example.ajay.cryptoapp.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptocurrencyApplication : Application(), HasActivityInjector {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        /**
         * Initialise dagger over here
         */

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(BuildConfig.URL))
                .build().inject(this)

    }


    override fun activityInjector(): AndroidInjector<Activity>  = activityInjector
}