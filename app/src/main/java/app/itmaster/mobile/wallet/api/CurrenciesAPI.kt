package app.itmaster.mobile.wallet.api

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CurrenciesAPI {

    fun fetchData(callback: (String?) -> Unit) {

        val url = "https://api.bluelytics.com.ar/v2/latest"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        // Realizar la solicitud de manera asíncrona
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejar la falla de la solicitud
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

