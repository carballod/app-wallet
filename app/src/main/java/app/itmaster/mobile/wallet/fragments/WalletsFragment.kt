package app.itmaster.mobile.wallet.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.itmaster.mobile.wallet.R
import app.itmaster.mobile.wallet.tabwallet.Wallet
import app.itmaster.mobile.wallet.tabwallet.FormWalletActivity
import app.itmaster.mobile.wallet.tabwallet.adapter.WalletAdapter
import app.itmaster.mobile.wallet.tabwallet.database.WalletDatabase
import kotlinx.coroutines.launch

class WalletsFragment : Fragment() {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var recyclerView: RecyclerView

    companion object {
        const val REQUEST_CODE_ADD_WALLET = 1
    }

    private suspend fun getAllWallets(): List<Wallet> {
        val database = WalletDatabase.getInstance(requireContext())
        val walletDao = database.getWalletDao()
        return walletDao.getAllWallets()
    }

    private fun initRecyclerView(view: View, wallets: List<Wallet>) {
        recyclerView = view.findViewById(R.id.recycler_fragment_wallet)
        val recyclerLayout = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayout

        // Crear el adaptador con la lista de Wallets
        walletAdapter = WalletAdapter(wallets)

        // Asignar el adaptador al RecyclerView
        recyclerView.adapter = walletAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        lifecycleScope.launch {
            val wallets = getAllWallets()

            // Inicializar el RecyclerView con la lista de Wallets
            initRecyclerView(view, wallets)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.wallet, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_new_wallet -> {
                startActivityForResult(
                    Intent(requireContext(), FormWalletActivity::class.java),
                    REQUEST_CODE_ADD_WALLET
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_WALLET && resultCode == Activity.RESULT_OK) {
            lifecycleScope.launch {
                // Actualizar la lista en el fragmento después de agregar un nuevo wallet
                val wallets = getAllWallets()
                walletAdapter.updateWallets(wallets)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }
}
