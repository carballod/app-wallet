package app.itmaster.mobile.wallet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val selectButton: Button = findViewById(R.id.btn_settings_select)
        val radioGroup: RadioGroup = findViewById(R.id.currencyRadioGroup)

        selectButton.setOnClickListener {
            val selectedCurrency = when (radioGroup.checkedRadioButtonId) {
                R.id.btn_radio_usd -> "USD"
                R.id.btn_radio_arg -> "ARS"
                R.id.btn_radio_btc -> "BTC"
                else -> "USD"
            }

            saveSelectedCurrency(selectedCurrency)
            finish()
        }
    }


    private fun saveSelectedCurrency(currency: String) {
        val sharedPreferences = getSharedPreferences("settingsPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selectedCurrency", currency)
        editor.apply()
    }

}