package app.itmaster.mobile.wallet.tabwallet.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.itmaster.mobile.wallet.tabwallet.Wallet

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallet_table ORDER BY id DESC")
    suspend fun getAllWallets():List<Wallet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallet: Wallet)

}