package app.itmaster.mobile.wallet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.api.CurrenciesAPI
import org.json.JSONObject

class InfoFragment : Fragment() {

    private lateinit var valueBlueBuy: TextView
    private lateinit var valueBlueSell: TextView
    private lateinit var valueOfficialSell: TextView
    private lateinit var valueOfficialBuy: TextView
    private lateinit var valueBTCLow: TextView
    private lateinit var valueBTCHigh: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valueBlueBuy = view.findViewById(R.id.info_tv_blue_sell)
        valueBlueSell = view.findViewById(R.id.info_tv_blue_buy)
        valueOfficialSell = view.findViewById(R.id.info_tv_oficial_sell)
        valueOfficialBuy = view.findViewById(R.id.info_tv_oficial_buy)
        valueBTCLow = view.findViewById(R.id.info_tv_btc_low)
        valueBTCHigh = view.findViewById(R.id.info_tv_btc_high)

        val currenciesAPI = CurrenciesAPI()

        currenciesAPI.fetchExchangeRates(CurrenciesAPI.URL_DOLAR) { responseData ->
            activity?.runOnUiThread {
                if (responseData != null) {
                    updateValuesUSD(valueBlueBuy, "blue", "value_buy", responseData)
                    updateValuesUSD(valueBlueSell, "blue", "value_sell", responseData)
                    updateValuesUSD(valueOfficialBuy, "oficial", "value_buy", responseData)
                    updateValuesUSD(valueOfficialSell, "oficial", "value_sell", responseData)
                } else {
                    Log.e("InfoFragment", "Error al procesar la respuesta del servidor")
                }
            }
        }

        currenciesAPI.fetchExchangeRates(CurrenciesAPI.URL_BTC) { responseData ->
            activity?.runOnUiThread {
                if (responseData != null) {
                    updateValuesBTC(valueBTCLow, responseData, "low")
                    updateValuesBTC(valueBTCHigh, responseData, "high")
                } else {
                    Log.e("InfoFragment", "Error al procesar la respuesta del servidor")
                }
            }
        }

    }

    private fun updateValuesUSD(textView: TextView, category: String, field: String, responseData: String) {
        val jsonObject = JSONObject(responseData)
        val categoryObject = jsonObject.getJSONObject(category)
        val value = categoryObject.getDouble(field)
        textView.text = "$${value.toInt()}"
    }

    private fun updateValuesBTC(textView: TextView, responseData: String, valueType: String) {
        try {
            val jsonObject = JSONObject(responseData)
            val dataArray = jsonObject.getJSONArray("data")

            var btcObject: JSONObject? = null
            for (i in 0 until dataArray.length()) {
                val currentObject = dataArray.getJSONObject(i)
                val pair = currentObject.getString("pair")
                if (pair == "BTC:USD") {
                    btcObject = currentObject
                    break
                }
            }

            if (btcObject != null) {
                val value = if (valueType == "low") {
                    btcObject.getString("low")
                } else {
                    btcObject.getString("high")
                }
                textView.text = "USD $value"
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}