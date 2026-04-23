package my.tatarinov.kflow.domain.models

data class RegisterRequestDomain(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val sex: String
)