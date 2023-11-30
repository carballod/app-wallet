package app.itmaster.mobile.wallet.tabwallet.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.Wallet

class WalletViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvName = view.findViewById<TextView>(R.id.tv_recycler_name)
    val tvAmount = view.findViewById<TextView>(R.id.tv_recycler_amount)
    val tvCurrency = view.findViewById<TextView>(R.id.tv_recycler_currency)
    val image = view.findViewById<ImageView>(R.id.iv_recycler_wallet)

    fun render(walletModel: Wallet) {
        tvName.text = walletModel.name
        tvAmount.text = walletModel.amount.toString()
        tvCurrency.text = walletModel.currency
    }

}