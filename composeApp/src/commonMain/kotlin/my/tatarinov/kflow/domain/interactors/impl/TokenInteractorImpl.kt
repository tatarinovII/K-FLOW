package my.tatarinov.kflow.domain.interactors.impl

import my.tatarinov.kflow.data.storage.TokenStorage
import my.tatarinov.kflow.domain.interactors.TokenInteractor

class TokenInteractorImpl(
    private val storage: TokenStorage
): TokenInteractor {
    override fun getToken(): String {
        return storage.getToken() ?: ""
    }

    override fun clearToken() {
        storage.clearToken()
    }
}