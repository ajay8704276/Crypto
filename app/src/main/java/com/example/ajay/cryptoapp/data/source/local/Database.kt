package com.example.ajay.cryptoapp.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ajay.cryptoapp.data.Cryptocurrency


@Database(entities = arrayOf(Cryptocurrency::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun cyrptocurrenciesDao(): CryptocurrencyDao

}