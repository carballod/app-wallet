package app.itmaster.mobile.wallet.tabwallet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.Wallet

class WalletAdapter(private var walletList: List<Wallet>) : RecyclerView.Adapter<WalletViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WalletViewHolder(layoutInflater.inflate(R.layout.item_wallet, parent, false))
    }

    override fun getItemCount(): Int {
        return walletList.size
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val item = walletList[position]
        holder.render(item)
    }

    fun updateWallets(newWalletList: List<Wallet>) {
        walletList = newWalletList
        notifyDataSetChanged()
    }

}