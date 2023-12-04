package app.itmaster.mobile.wallet.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.SettingsActivity
import app.itmaster.mobile.wallet.login.LoginActivity
import app.itmaster.mobile.wallet.tabwallet.FormWalletActivity
import app.itmaster.mobile.wallet.tabwallet.database.WalletDatabase
import kotlinx.coroutines.launch

class DashboardsFragment : Fragment() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboards, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(requireContext(), SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                clearLoginState()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        lifecycleScope.launch {
            val selectedCurrency = getSelectedCurrency()
            val totalAmount = getTotalAmount(selectedCurrency)
            updateUI(totalAmount, selectedCurrency)
        }

        val refreshButton: Button = requireView().findViewById(R.id.btn_fragment_dashboards_refresh)
        refreshButton.setOnClickListener {
            refreshData()
        }
    }

    private suspend fun getTotalAmount(selectedCurrency: String): Double {
        val database = WalletDatabase.getInstance(requireContext())
        val walletDao = database.getWalletDao()
        val totalAmount = walletDao.getTotalAmount(selectedCurrency)

        return totalAmount
    }


    private fun updateUI(totalAmount: Double, selectedCurrency: String) {
        val currencyTextView: TextView = requireView().findViewById(R.id.tv_fragment_dashboards_currency)
        val totalTextView: TextView = requireView().findViewById(R.id.tv_fragment_dashboards_total)

        currencyTextView.text = selectedCurrency
        totalTextView.text = totalAmount.toString()
    }

    private fun getSelectedCurrency(): String {
        val sharedPreferences = requireContext().getSharedPreferences("settingsPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selectedCurrency", "USD") ?: "USD"
    }

    private fun refreshData() {
        lifecycleScope.launch {
            val selectedCurrency = getSelectedCurrency()
            val totalAmount = getTotalAmount(selectedCurrency)
            updateUI(totalAmount, selectedCurrency)
        }
    }

    private fun clearLoginState() {
        val sharedPreferences = requireContext().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}