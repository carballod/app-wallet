package app.itmaster.mobile.wallet.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Auth {
    suspend fun validateCredentials(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            delay(2000)
            username == "admin" && password == "123456"
        }
    }
}
