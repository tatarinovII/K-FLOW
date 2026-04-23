package my.tatarinov.kflow.domain.interactors

interface AuthInteractor {

    suspend fun logIn(email: String, password: String): Result<String>

    suspend fun register(
        email: String, password: String, firstName: String, lastName: String, sex: String
    ): Result<String>
}