package my.tatarinov.kflow.domain.interactors

interface TokenInteractor {
    fun getToken(): String
    fun clearToken()
}