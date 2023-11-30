package app.itmaster.mobile.wallet.tabwallet.database

import app.itmaster.mobile.wallet.tabwallet.Converters
import app.itmaster.mobile.wallet.tabwallet.Wallet
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wallet::class], version = 1)
abstract class WalletDatabase: RoomDatabase() {
    abstract fun getWalletDao(): WalletDao

    companion object {
        @Volatile
        private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): WalletDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                WalletDatabase::class.java, "wallet_database"
            ).build()
        }
    }
}
