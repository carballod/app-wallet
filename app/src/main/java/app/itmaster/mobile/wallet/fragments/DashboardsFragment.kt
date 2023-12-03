package app.itmaster.mobile.wallet.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.database.WalletDatabase
import kotlinx.coroutines.launch

class DashboardsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val totalAmountInUSD = getTotalAmountInUSD()
            updateUI(totalAmountInUSD)
        }

        val refreshButton: Button = requireView().findViewById(R.id.btn_fragment_dashboards_refresh)
        refreshButton.setOnClickListener {
            refreshData()
        }
    }

    private suspend fun getTotalAmountInUSD(): Double {
        val database = WalletDatabase.getInstance(requireContext())
        val walletDao = database.getWalletDao()
        return walletDao.getTotalAmountInUSD()
    }

    private fun updateUI(totalAmountInUSD: Double) {
        val currencyTextView: TextView = requireView().findViewById(R.id.tv_fragment_dashboards_currency)
        val totalTextView: TextView = requireView().findViewById(R.id.tv_fragment_dashboards_total)

        currencyTextView.text = "USD"
        totalTextView.text = totalAmountInUSD.toString()
    }

    private fun refreshData() {
        lifecycleScope.launch {
            val totalAmountInUSD = getTotalAmountInUSD()
            updateUI(totalAmountInUSD)
        }
    }

}