package app.itmaster.mobile.wallet.tabwallet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.adapter.WalletAdapter
import app.itmaster.mobile.wallet.tabwallet.database.WalletDatabase
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class WalletActivity : AppCompatActivity() {

    private lateinit var walletAdapter: WalletAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val lblName: TextInputLayout = findViewById(R.id.wallet_lbl_name)
        val lblAmount: TextInputLayout = findViewById(R.id.wallet_lbl_amount)
        val lblCurrency: TextInputLayout = findViewById(R.id.wallet_lbl_currency)
        val btnSave: Button = findViewById(R.id.wallet_btn_save)

        walletAdapter = WalletAdapter(emptyList())

        btnSave.setOnClickListener {
            val name = lblName.editText?.text.toString()
            val amount = lblAmount.editText?.text.toString().toDouble()
            val currency = lblCurrency.editText?.text.toString()

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
            val database = WalletDatabase.getInstance(this@WalletActivity)
            val dao = database.getWalletDao()
            dao.insert(wallet)

            // Obtener la lista actualizada después de la inserción
            val updatedWallets = dao.getAllWallets()

            // Notificar al adaptador del cambio en la lista
            walletAdapter.updateWallets(updatedWallets)
        }
    }
}
