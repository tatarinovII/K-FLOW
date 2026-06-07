package my.tatarinov.kflow.data.storage

import com.liftric.kvault.KVault

class TokenStorage(private val vault: KVault) {

    fun saveToken(token: String) {
        vault.set(TOKEN_KEY, token)
    }

    fun getToken(): String? = vault.string(TOKEN_KEY)

    fun clearToken() {
        vault.deleteObject(TOKEN_KEY)
    }

    private companion object {
        const val TOKEN_KEY = "token"
    }
}