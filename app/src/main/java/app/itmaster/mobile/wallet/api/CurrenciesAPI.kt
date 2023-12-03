package app.itmaster.mobile.wallet.api

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CurrenciesAPI {

    companion object {
        const val URL_DOLAR = "https://api.bluelytics.com.ar/v2/latest"
        const val URL_BTC = "https://cex.io/api/tickers/BTC/USD"
    }

    fun fetchExchangeRates(url: String, callback: (String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        // Realizar la solicitud de manera asíncrona
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                callback(responseData)
            }
        })
    }
}


