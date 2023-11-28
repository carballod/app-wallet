package app.itmaster.mobile.wallet.tabwallet

data class Wallet (
    val name: String,
    val amount: Double,
    val currency: List<String> = listOf("USD", "EUR", "BTC", "ARGS")
)