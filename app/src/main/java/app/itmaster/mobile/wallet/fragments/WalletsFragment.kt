package app.itmaster.mobile.wallet.fragments

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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.Wallet
import app.itmaster.mobile.wallet.tabwallet.WalletActivity
import app.itmaster.mobile.wallet.tabwallet.adapter.WalletAdapter
import kotlinx.coroutines.launch

class WalletsFragment : Fragment() {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var walletList: List<Wallet>

    private fun initRecyclerView(view: View) {

        walletList = listOf(
            Wallet("Wallet 1", 100.0, listOf("USD")),
            Wallet("Wallet 2", 200.0, listOf("EUR")),
        )

        walletAdapter = WalletAdapter(walletList)
        recyclerView = view.findViewById(R.id.recycler_fragment_wallet)
        val recyclerLayout = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayout
        recyclerView.adapter = walletAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            initRecyclerView(view)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.wallet, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_new_wallet -> {
                startActivity(Intent(requireContext(), WalletActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

}