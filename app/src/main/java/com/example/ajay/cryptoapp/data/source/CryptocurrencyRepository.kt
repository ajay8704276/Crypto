package com.example.ajay.cryptoapp.data.source

import com.example.ajay.cryptoapp.data.Cryptocurrency
import com.example.ajay.cryptoapp.data.source.local.CryptocurrencyDao
import com.example.ajay.cryptoapp.data.source.remote.ApiInterface
import com.example.ajay.cryptoapp.utils.Constants
import com.example.ajay.cryptoapp.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class CryptocurrencyRepository @Inject constructor(val apiInterface: ApiInterface,
                                                   val cryptocurrencyDao: CryptocurrencyDao, private val utils: Utils) {


    fun getCryptocurrencies(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {


        val hasConnection = utils.isConnectedToInternet()

        var observableFromApi: Observable<List<Cryptocurrency>>? = null


        if (hasConnection) {
            observableFromApi = getCryptocurrencyFromApi()
        }

        val observableFromDb = getCryptocurrencyFromDb(limit, offset)


        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb

    }

    private fun getCryptocurrencyFromDb(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {

        return cryptocurrencyDao.queryCryptocurrencies(limit, offset)
                .toObservable()
                .doOnNext { }
    }

    private fun getCryptocurrencyFromApi(): Observable<List<Cryptocurrency>>? {

        return apiInterface.getCryptocurrencies(Constants.START_ZERO_VALUE)
                .doOnNext {
                    for (item in it) {
                        cryptocurrencyDao.insertCryptocurrency(item)
                    }
                }
    }


}