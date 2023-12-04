package app.itmaster.mobile.wallet.tabwallet

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.adapter.WalletAdapter
import app.itmaster.mobile.wallet.tabwallet.database.WalletDatabase
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class FormWalletActivity : AppCompatActivity() {

    private lateinit var walletAdapter: WalletAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val lblName: TextInputLayout = findViewById(R.id.wallet_lbl_name)
        val lblAmount: TextInputLayout = findViewById(R.id.wallet_lbl_amount)
        val btnSave: Button = findViewById(R.id.wallet_btn_save)

        walletAdapter = WalletAdapter(emptyList())

        // Spinner
        val spinnerCurrency: Spinner = findViewById(R.id.wallet_spinner_currency)
        val currencyOptions = arrayOf("USD", "BTC", "ARS")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency.adapter = adapter

        btnSave.setOnClickListener {
            val name = lblName.editText?.text.toString()
            val amount = lblAmount.editText?.text.toString().toDouble()
            val currency = spinnerCurrency.selectedItem.toString()

            if (name.isNotEmpty()) {
                val wallet = Wallet(name = name, amount = amount, currency = currency)
                insertWallet(wallet)

                // Establecer el resultado como OK para indicar que se ha realizado una acción
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                lblName.error = "El nombre es obligatorio"
            }
        }
    }

    private fun insertWallet(wallet: Wallet) {
        lifecycleScope.launch {
            val database = WalletDatabase.getInstance(this@FormWalletActivity)
            val dao = database.getWalletDao()
            dao.insert(wallet)

            // Obtener la lista actualizada después de la inserción
            val updatedWallets = dao.getAllWallets()

            // Notificar al adaptador del cambio en la lista
            walletAdapter.updateWallets(updatedWallets)
        }
    }
}
