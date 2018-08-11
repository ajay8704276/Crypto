package com.example.ajay.cryptoapp.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.example.ajay.cryptoapp.data.Cryptocurrency
import com.example.ajay.cryptoapp.data.source.CryptocurrencyRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CryptocurrenciesViewModel @Inject constructor(private val cryptocurrencyRepository: CryptocurrencyRepository): ViewModel() {

    var cryptocurrenciesResult :MutableLiveData<List<Cryptocurrency>> = MutableLiveData()
    var cryptocurrenciesError: MutableLiveData<String> = MutableLiveData()
    var cryptocurrenciesLoader: MutableLiveData<Boolean> = MutableLiveData()



    lateinit var disposableObserver: DisposableObserver<List<Cryptocurrency>>


    fun cryptocurrenciesResult():LiveData<List<Cryptocurrency>>{
        return cryptocurrenciesResult
    }

    fun cryptocurrenciesError():LiveData<String>{
        return cryptocurrenciesError
    }

    fun cryptocurrenciesLoader():LiveData<Boolean>{
        return cryptocurrenciesLoader
    }


    fun loadCryptocurrencies(limit:Int ,offset:Int){

        disposableObserver = object : DisposableObserver<List<Cryptocurrency>>(), Observer<List<Cryptocurrency>> {
            override fun onChanged(t: List<Cryptocurrency>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onComplete() {

            }

            override fun onNext(t: List<Cryptocurrency>) {

                cryptocurrenciesResult.postValue(t)
                cryptocurrenciesLoader.postValue(false)

            }

            override fun onError(e: Throwable) {

                cryptocurrenciesError.postValue(e.message)
                cryptocurrenciesLoader.postValue(false)
            }


        }

        cryptocurrencyRepository.getCryptocurrencies(limit,offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }


    fun disposeElements(){

        if (disposableObserver!=null && disposableObserver.isDisposed) disposableObserver.dispose()
    }
}