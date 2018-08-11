package com.example.ajay.cryptoapp.data.source.remote

import com.example.ajay.cryptoapp.data.Cryptocurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("ticker/")
    fun getCryptocurrencies(@Query("start") start: String): Observable<List<Cryptocurrency>>

}