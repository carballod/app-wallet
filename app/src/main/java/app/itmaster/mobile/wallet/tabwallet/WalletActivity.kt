package app.itmaster.mobile.wallet.tabwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import app.itmaster.mobile.wallet.R
import com.google.android.material.textfield.TextInputLayout

class WalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val currencyOptions = arrayOf("USD", "EUR", "BTC", "ARGS")

        val lblName: TextInputLayout = findViewById(R.id.wallet_lbl_name)
        val lblAmount: TextInputLayout = findViewById(R.id.wallet_lbl_amount)
        val spinnerCurrency: Spinner = findViewById(R.id.wallet_spinner_currency)
        val btnSave: Button = findViewById(R.id.wallet_btn_save)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency.adapter = adapter

        btnSave.setOnClickListener {
        }

    }
}