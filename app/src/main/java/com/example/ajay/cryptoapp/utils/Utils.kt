package com.example.ajay.cryptoapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class Utils @Inject constructor(val context: Context) {

    @SuppressLint("ServiceCast")
    fun isConnectedToInternet(): Boolean {

        val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false

    }
}