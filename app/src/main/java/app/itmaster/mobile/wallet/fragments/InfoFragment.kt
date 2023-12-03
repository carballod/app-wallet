package app.itmaster.mobile.wallet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.api.CurrenciesAPI

class InfoFragment : Fragment() {

    private lateinit var tvFiPrueba: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvFiPrueba = view.findViewById(R.id.tv_fi_prueba)

        val currenciesAPI = CurrenciesAPI()

        currenciesAPI.fetchData { responseData ->
            activity?.runOnUiThread {
                if (responseData != null) {
                    tvFiPrueba.text = responseData
                } else {
                    tvFiPrueba.text = "Error: Respuesta nula"
                }
            }
        }

    }



}