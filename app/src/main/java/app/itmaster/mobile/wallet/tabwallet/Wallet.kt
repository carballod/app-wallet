package app.itmaster.mobile.wallet.tabwallet

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class Wallet (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "currency") val currency: String
)
